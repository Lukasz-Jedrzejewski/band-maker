package com.legion.user.entity

import com.legion.common.CrmEntity
import java.time.Instant
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
data class User (

        @Id
        @Column(name = "id", unique = true)
        override val id: UUID = UUID.randomUUID(),

        @Column(name = "email")
        val email: String,

        @Column(name = "password")
        val password: String,

        @Column(name = "creation_date")
        override val createDate: Instant = Instant.now(),

        @Column(name = "update_date")
        override val updateDate: Instant? = Instant.now(),

        @Column(name = "removed")
        override val removed: Boolean = false

) : CrmEntity