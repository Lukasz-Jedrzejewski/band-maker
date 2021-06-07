package com.legion.personalData.boundary

import com.legion.personalData.control.PersonalDataService
import com.legion.personalData.entity.PersonalData
import com.legion.personalData.entity.PersonalDataRequest
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
@RequestMapping("/personal-data")
class PersonalDataController @Autowired constructor(
        private val personalDataService: PersonalDataService
){

    @PostMapping("/{userId}")
    fun createOrUpdate(@RequestBody personalDataRequest: PersonalDataRequest,
                       @PathVariable userId: UUID): ResponseEntity<PersonalData> {
        return ResponseEntity(personalDataService.createOrUpdate(userId, personalDataRequest), HttpStatus.CREATED)
    }
}