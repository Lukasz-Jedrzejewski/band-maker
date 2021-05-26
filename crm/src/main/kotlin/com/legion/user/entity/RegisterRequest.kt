package com.legion.user.entity

import com.fasterxml.jackson.annotation.JsonCreator


data class RegisterRequest (
        val email: String,
        val password: String,
        val userType: UserType
)
