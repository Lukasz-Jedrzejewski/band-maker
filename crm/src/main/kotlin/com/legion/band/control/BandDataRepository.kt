package com.legion.band.control

import com.legion.band.entity.BandData
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BandDataRepository : JpaRepository<BandData, UUID> {
    fun existsByUserId(userId: UUID): Boolean
    fun findByUserId(userId: UUID): BandData
}