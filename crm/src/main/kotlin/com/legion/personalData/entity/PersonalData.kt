package com.legion.personalData.entity

import com.legion.common.CrmEntity
import com.legion.common.DataEntity
import com.legion.user.entity.User
import java.time.Instant
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "personal_data")
data class PersonalData (

        @Id
        @Column(name = "id", unique = true)
        override val id: UUID = UUID.randomUUID(),

        @Column(name = "name")
        override val name: String,

        @Column(name = "surname")
        val surname: String,

        @Column(name = "city")
        override val city: String,

        @Column(name = "alias")
        val alias: String?,

        @Column(name = "instrument")
        val instrument: Instrument?,

        @Column(name = "vocal")
        val vocal: Boolean,

        @Column(name = "description")
        val description: String,

        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        val user: User,

        @Column(name = "creation_date")
        override val createDate: Instant = Instant.now(),

        @Column(name = "update_date")
        override val updateDate: Instant? = Instant.now(),

        @Column(name = "removed")
        override val removed: Boolean = false

) : CrmEntity, DataEntity