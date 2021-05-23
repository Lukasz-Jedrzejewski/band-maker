package com.legion.user.entity

data class RegisterRequest(
        val email: String,
        val password: String,
        val userType: UserType
)
