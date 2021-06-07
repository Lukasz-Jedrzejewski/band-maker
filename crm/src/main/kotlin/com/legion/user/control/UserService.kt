package com.legion.user.control

import com.legion.user.entity.RegisterRequest
import com.legion.user.entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import mu.KotlinLogging
import java.util.*

@Service
class UserService @Autowired constructor(
        private val userRepository: UserRepository
) {
    companion object {
        private val logger = KotlinLogging.logger {}
    }

    fun register(registerRequest: RegisterRequest) :User {
        val user = User(email = registerRequest.email,
                password = registerRequest.password,
                userType = registerRequest.userType)
        val savedUser = userRepository.save(user)
        logger.debug { "User with email: ${savedUser.email} created" }

        return savedUser;
    }

    fun getById(userId: UUID): User {
        return userRepository.getById(userId)
    }

    fun changePassword(userId: UUID, newPassword: String): User {
        val user = userRepository.getById(userId)
        val updated = user.copy(password = newPassword)
        val updatedUser = userRepository.save(updated)
        logger.debug { "Password for user with id: $userId updated" }

        return updatedUser
    }

    fun existsByEmail(email: String): Boolean {
        return userRepository.existsByEmail(email)
    }
}