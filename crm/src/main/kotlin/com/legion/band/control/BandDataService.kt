package com.legion.band.control

import com.legion.band.entity.BandData
import com.legion.band.entity.BandDataRequest
import com.legion.user.control.UserService
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class BandDataService @Autowired constructor(
        private val bandDataRepository: BandDataRepository,
        private val userService: UserService
) {
    companion object {
        private val logger = KotlinLogging.logger {}
    }

    private fun checkIfExists(userId: UUID): Boolean {
        return bandDataRepository.existsByUserId(userId)
    }

    fun createOrUpdate(userId: UUID, bandDataRequest: BandDataRequest): BandData {
        return if (!checkIfExists(userId)) {
            create(userId, bandDataRequest)
        } else {
            val bandData = bandDataRepository.findByUserId(userId)
            update(bandData, bandDataRequest)
        }
    }

    private fun create(userId: UUID, bandDataRequest: BandDataRequest): BandData {
        val user = userService.getById(userId)
        val bandData = BandData(
                name = bandDataRequest.name,
                city = bandDataRequest.city,
                phoneNumber = bandDataRequest.phoneNumber,
                genre = bandDataRequest.genre,
                description = bandDataRequest.description,
                user = user
        )
        val createdData = bandDataRepository.save(bandData)
        logger.debug { "Band data for user with id: $userId created" }

        return createdData
    }

    private fun update(bandData: BandData, bandDataRequest: BandDataRequest): BandData {
        val updated = bandData.copy(
                name = bandDataRequest.name,
                city = bandDataRequest.city,
                phoneNumber = bandDataRequest.phoneNumber,
                genre = bandDataRequest.genre,
                description = bandDataRequest.description
        )
        val updatedData = bandDataRepository.save(updated)
        logger.debug { "Band data for user with id: ${bandData.user.id} updated" }

        return updatedData
    }
}