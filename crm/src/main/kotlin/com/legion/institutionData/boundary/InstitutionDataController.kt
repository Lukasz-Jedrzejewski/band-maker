package com.legion.institutionData.boundary

import com.legion.institutionData.control.InstitutionDataService
import com.legion.institutionData.entity.InstitutionData
import com.legion.institutionData.entity.InstitutionDataRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import java.util.*

@Controller
@RequestMapping("/institution-data")
class InstitutionDataController @Autowired constructor(
        private val institutionDataService: InstitutionDataService
){

    @PostMapping("/{userId}")
    fun createOrUpdate(@RequestBody institutionDataRequest: InstitutionDataRequest,
                       @PathVariable userId: UUID): ResponseEntity<InstitutionData> {
        return ResponseEntity(institutionDataService.createOrUpdate(userId, institutionDataRequest), HttpStatus.CREATED)
    }
}