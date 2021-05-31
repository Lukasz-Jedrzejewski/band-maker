package com.legion.band.entity

data class BandDataRequest (
        val name: String,
        val city: String,
        val phoneNumber: String?,
        val genre: String,
        val description: String
)