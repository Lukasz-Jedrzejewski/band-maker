package com.legion.user.tools

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.sql.DataSource

@Component
class DatabaseCleaner {

    @Autowired
    private lateinit var datasource: DataSource

    companion object {
        private const val DISABLE_FK_QUERY = "SET REFERENTIAL_INTEGRITY FALSE"
        private const val ENABLE_FK_QUERY = "SET REFERENTIAL_INTEGRITY TRUE"
        private const val SELECT_TABLE_NAME_QUERY = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES  where TABLE_SCHEMA='PUBLIC'"
        private const val SELECT_SEQUENCE_NAME_QUERY = "SELECT SEQUENCE_NAME FROM INFORMATION_SCHEMA.SEQUENCES WHERE SEQUENCE_SCHEMA='PUBLIC'"
    }

    fun clearH2() {
        val connection = datasource.connection
        val statement = connection.createStatement()

        statement.execute(DISABLE_FK_QUERY)

        val tables = HashSet<String>()
        var resultSet = statement.executeQuery(SELECT_TABLE_NAME_QUERY)
        while (resultSet.next()) {
            tables.add(resultSet.getString(1))
        }
        resultSet.close()

        tables.forEach { statement.executeUpdate(createTruncateTableQuery(it)) }

        val sequences = HashSet<String>()
        resultSet = statement.executeQuery(SELECT_SEQUENCE_NAME_QUERY)
        while (resultSet.next()) {
            sequences.add(resultSet.getString(1))
        }
        resultSet.close()

        sequences.forEach { statement.executeUpdate(createUpdateSequenceQuery(it)) }

        statement.execute(ENABLE_FK_QUERY)
        statement.close()
        connection.close()
    }

    private fun createTruncateTableQuery(tableName: String): String {
        return """TRUNCATE TABLE "$tableName"""".trimIndent()
    }

    private fun createUpdateSequenceQuery(sequence: String): String {
        return "ALTER SEQUENCE $sequence RESTART WITH 1"
    }
}
