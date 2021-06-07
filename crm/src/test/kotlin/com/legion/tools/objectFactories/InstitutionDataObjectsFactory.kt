package com.legion.tools.objectFactories

import com.legion.institutionData.entity.InstitutionData
import com.legion.institutionData.entity.InstitutionDataRequest
import com.legion.user.entity.User
import java.time.Instant
import java.util.*

fun getTestInstitutionData(
        id: UUID = UUID.randomUUID(),
        name: String = "${UUID.randomUUID()}",
        city: String = "${UUID.randomUUID()}",
        street: String = "${UUID.randomUUID()}",
        streetNumber: String = "1",
        localNumber: String = "1",
        phoneNumber: String = "123123123",
        description: String = "${UUID.randomUUID()}",
        user: User,
        createDate: Instant = Instant.now(),
        updateDate: Instant? = null,
        removed: Boolean = false
) = InstitutionData(id, name, city, street, streetNumber, localNumber, phoneNumber,
        description, user, createDate, updateDate, removed)

fun getTestInstitutionDataRequest(
        name: String = "${UUID.randomUUID()}",
        city: String = "${UUID.randomUUID()}",
        street: String = "${UUID.randomUUID()}",
        streetNumber: String = "2",
        localNumber: String = "2",
        phoneNumber: String = "123451234",
        description: String = "${UUID.randomUUID()}"
) = InstitutionDataRequest(name, city, street, streetNumber, localNumber, phoneNumber, description)