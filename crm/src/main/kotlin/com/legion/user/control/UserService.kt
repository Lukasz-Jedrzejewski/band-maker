package com.legion.user.control

import com.legion.user.entity.RegisterRequest
import com.legion.user.entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService(
        private val userRepository: UserRepository
) {

    fun register(registerRequest: RegisterRequest) :User {
        val user = User(email = registerRequest.email,
                password = registerRequest.password,
                userType = registerRequest.userType)
        return userRepository.save(user);
    }
}