package com.legion.institutionData.control

import com.legion.institutionData.entity.InstitutionData
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface InstitutionDataRepository : JpaRepository<InstitutionData, UUID> {
    fun existsByUserId(userId: UUID): Boolean
    fun findByUserId(userId: UUID): InstitutionData
}