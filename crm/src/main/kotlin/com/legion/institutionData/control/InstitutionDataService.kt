package com.legion.institutionData.control

import com.legion.institutionData.entity.InstitutionData
import com.legion.institutionData.entity.InstitutionDataRequest
import com.legion.user.control.UserService
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class InstitutionDataService @Autowired constructor(
        private val institutionDataRepository: InstitutionDataRepository,
        private val userService: UserService
) {
    companion object {
        private val logger = KotlinLogging.logger {}
    }

    private fun checkIfExists(userId: UUID): Boolean {
        return institutionDataRepository.existsByUserId(userId)
    }

    fun createOrUpdate(userId: UUID, institutionDataRequest: InstitutionDataRequest): InstitutionData {
        return if (!checkIfExists(userId)) {
            create(userId, institutionDataRequest)
        } else {
            val institutionData = institutionDataRepository.findByUserId(userId)
            update(institutionData, institutionDataRequest)
        }
    }

    private fun create(userId: UUID, institutionDataRequest: InstitutionDataRequest): InstitutionData {
        val user = userService.getById(userId)
        val institutionData = InstitutionData(
                name = institutionDataRequest.name,
                city = institutionDataRequest.city,
                street = institutionDataRequest.street,
                streetNumber = institutionDataRequest.streetNumber,
                localNumber = institutionDataRequest.localNumber,
                phoneNumber = institutionDataRequest.phoneNumber,
                description = institutionDataRequest.description,
                user = user
        )
        val createdData = institutionDataRepository.save(institutionData)
        logger.debug { "Institution data for user with id: $userId created" }

        return createdData
    }

    private fun update(institutionData: InstitutionData, institutionDataRequest: InstitutionDataRequest): InstitutionData {
        val updated = institutionData.copy(
                name = institutionDataRequest.name,
                city = institutionDataRequest.city,
                street = institutionDataRequest.street,
                streetNumber = institutionDataRequest.streetNumber,
                localNumber = institutionDataRequest.localNumber,
                phoneNumber = institutionDataRequest.phoneNumber,
                description = institutionDataRequest.description,
        )
        val updatedData = institutionDataRepository.save(updated)
        logger.debug { "Institution data for user with id: ${institutionData.user.id} updated" }

        return updatedData
    }
}