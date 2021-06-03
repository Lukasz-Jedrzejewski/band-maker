package com.legion.user.boundary

import com.legion.user.control.UserService
import com.legion.user.entity.RegisterRequest
import com.legion.user.entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

@Controller
@RequestMapping("/users")
class UserController @Autowired constructor(
        private val userService: UserService
){

    @PostMapping
    fun register(@RequestBody registerRequest: RegisterRequest): ResponseEntity<User> {
        return ResponseEntity(userService.register(registerRequest), HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun changePassword(@PathVariable id: UUID,
                       @RequestParam newPassword: String): ResponseEntity<User> {
        return ResponseEntity(userService.changePassword(id, newPassword), HttpStatus.OK)
    }

    @GetMapping
    fun existByEmail(@RequestParam email: String): ResponseEntity<Boolean> {
        return ResponseEntity(userService.existsByEmail(email), HttpStatus.OK)
    }
}