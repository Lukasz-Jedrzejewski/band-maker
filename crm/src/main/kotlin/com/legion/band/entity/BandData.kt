package com.legion.band.entity

import com.legion.common.CrmEntity
import com.legion.common.DataEntity
import com.legion.user.entity.User
import java.time.Instant
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "band_data")
data class BandData (

        @Id
        @Column(name = "id", unique = true)
        override val id: UUID = UUID.randomUUID(),

        @Column(name = "name")
        override val name: String,

        @Column(name = "city")
        override val city: String,

        @Column(name = "phone_number")
        val phoneNumber: String?,

        @Column(name = "genre")
        val genre: String,

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