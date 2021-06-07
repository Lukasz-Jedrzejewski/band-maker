package com.legion.personalData.control

import com.legion.personalData.entity.PersonalData
import com.legion.personalData.entity.PersonalDataRequest
import com.legion.user.control.UserService
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class PersonalDataService @Autowired constructor(
        private val personalDataRepository: PersonalDataRepository,
        private val userService: UserService
) {
    companion object {
        private val logger = KotlinLogging.logger {}
    }

    private fun checkIfExists(userId: UUID): Boolean {
        return personalDataRepository.existsByUserId(userId)
    }

    fun createOrUpdate(userId: UUID, personalDataRequest: PersonalDataRequest): PersonalData {
        return if (!checkIfExists(userId)) {
            create(userId, personalDataRequest)
        } else {
            val personalData = personalDataRepository.findByUserId(userId)
            update(personalData, personalDataRequest)
        }
    }

    private fun create(userId: UUID, personalDataRequest: PersonalDataRequest): PersonalData {
        val user = userService.getById(userId)
        val personalData = PersonalData(
                name = personalDataRequest.name,
                surname = personalDataRequest.surname,
                city = personalDataRequest.city,
                alias = personalDataRequest.alias,
                instrument = personalDataRequest.instrument,
                vocal = personalDataRequest.vocal,
                description = personalDataRequest.description,
                user = user
        )
        val createdData = personalDataRepository.save(personalData)
        logger.debug { "Personal data for user with id: $userId created" }

        return createdData
    }

    private fun update(personalData: PersonalData, personalDataRequest: PersonalDataRequest): PersonalData {
        val updated = personalData.copy(
                name = personalDataRequest.name,
                surname = personalDataRequest.surname,
                city = personalDataRequest.city,
                alias = personalDataRequest.alias,
                instrument = personalDataRequest.instrument,
                vocal = personalDataRequest.vocal,
                description = personalDataRequest.description,
        )
        val updatedData = personalDataRepository.save(updated)
        logger.debug { "Personal data for user with id: ${personalData.user.id} updated" }

        return updatedData
    }
}