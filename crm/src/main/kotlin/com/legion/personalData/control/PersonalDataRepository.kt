package com.legion.personalData.control

import com.legion.personalData.entity.PersonalData
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PersonalDataRepository : JpaRepository<PersonalData, UUID> {
    fun existsByUserId(userId: UUID): Boolean
    fun findByUserId(userId: UUID): PersonalData
}