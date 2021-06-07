package com.legion.personalData.entity

data class PersonalDataRequest (
        val name: String,
        val surname: String,
        val city: String,
        val alias: String?,
        val instrument: Instrument?,
        val vocal: Boolean,
        val description: String,
)