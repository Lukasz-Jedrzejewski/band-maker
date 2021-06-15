package com.legion.test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class TestController @Autowired constructor(

) {

    @GetMapping("/test")
    fun test(): ResponseEntity<String> {
        return ResponseEntity("Hello crm and rest template", HttpStatus.OK)
    }
}