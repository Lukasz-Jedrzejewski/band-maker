package com.legion.tools.objectFactories

import com.legion.band.entity.BandData
import com.legion.band.entity.BandDataRequest
import com.legion.user.entity.User
import java.time.Instant
import java.util.*

fun getTestBandData(
        id: UUID = UUID.randomUUID(),
        name: String = "${UUID.randomUUID()}",
        city: String = "${UUID.randomUUID()}",
        phoneNumber: String = "123123123",
        genre: String = "${UUID.randomUUID()}",
        description: String = "${UUID.randomUUID()}",
        user: User,
        createDate: Instant = Instant.now(),
        updateDate: Instant? = null,
        removed: Boolean = false
) = BandData(id, name, city, phoneNumber, genre, description, user, createDate, updateDate, removed)

fun getTestBandDataRequest(
        name: String = "${UUID.randomUUID()}",
        city: String = "${UUID.randomUUID()}",
        phoneNumber: String? = "321321321",
        genre: String = "${UUID.randomUUID()}",
        description: String = "${UUID.randomUUID()}"
) = BandDataRequest(name, city, phoneNumber, genre, description)