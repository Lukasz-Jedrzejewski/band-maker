package com.legion.band.boundary

import com.legion.band.control.BandDataService
import com.legion.band.entity.BandData
import com.legion.band.entity.BandDataRequest
import mu.KotlinLogging
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
@RequestMapping("/band-data")
class BandDataController @Autowired constructor(
        private val bandDataService: BandDataService
){

    @PostMapping("/{userId}")
    fun createOrUpdate(@RequestBody bandDataRequest: BandDataRequest,
                       @PathVariable userId: UUID): ResponseEntity<BandData> {
        return ResponseEntity(bandDataService.createOrUpdate(userId, bandDataRequest), HttpStatus.CREATED)
    }
}