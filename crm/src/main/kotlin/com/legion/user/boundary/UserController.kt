package com.legion.user.boundary

import com.legion.user.control.UserService
import com.legion.user.entity.RegisterRequest
import com.legion.user.entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/users")
class UserController @Autowired constructor(
        private val userService: UserService
){

    @PostMapping
    fun register(@RequestBody registerRequest: RegisterRequest): ResponseEntity<User> {
        return ResponseEntity(userService.register(registerRequest), HttpStatus.CREATED)
    }
}