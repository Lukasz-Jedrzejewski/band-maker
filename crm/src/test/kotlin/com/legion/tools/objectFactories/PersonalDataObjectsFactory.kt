package com.legion.tools.objectFactories

import com.legion.personalData.entity.Instrument
import com.legion.personalData.entity.PersonalData
import com.legion.personalData.entity.PersonalDataRequest
import com.legion.user.entity.User
import java.time.Instant
import java.util.*

fun getTestPersonalData(
        id: UUID = UUID.randomUUID(),
        name: String = "${UUID.randomUUID()}",
        surname: String = "${UUID.randomUUID()}",
        city: String = "${UUID.randomUUID()}",
        alias: String = "${UUID.randomUUID()}",
        instrument: Instrument = Instrument.DRUMS,
        vocal: Boolean = false,
        description: String = "${UUID.randomUUID()}",
        user: User,
        createDate: Instant = Instant.now(),
        updateDate: Instant? = null,
        removed: Boolean = false
) = PersonalData(id, name, surname, city, alias, instrument, vocal, description, user, createDate, updateDate, removed)

fun getTestPersonalDataRequest(
        name: String = "${UUID.randomUUID()}",
        surname: String = "${UUID.randomUUID()}",
        city: String = "${UUID.randomUUID()}",
        alias: String = "${UUID.randomUUID()}",
        instrument: Instrument = Instrument.ELECTRIC_GUITAR,
        vocal: Boolean = true,
        description: String = "${UUID.randomUUID()}"
) = PersonalDataRequest(name, surname, city, alias, instrument, vocal, description)