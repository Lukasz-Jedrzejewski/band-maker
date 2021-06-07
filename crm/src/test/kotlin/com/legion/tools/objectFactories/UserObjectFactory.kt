package com.legion.tools.objectFactories

import com.legion.user.entity.RegisterRequest
import com.legion.user.entity.User
import com.legion.user.entity.UserType
import java.util.*

fun getTestUser(
        id: UUID = UUID.randomUUID(),
        email: String = "test@email.com",
        userType: UserType = UserType.MUSICIAN,
        password: String = "testPassword"
) = User(id, email, userType, password)

fun getTestRegisterRequest(
        email: String = "register@mail.com",
        password: String = "registerTestPassword",
        userType: UserType = UserType.MUSICIAN
) = RegisterRequest(email, password, userType)