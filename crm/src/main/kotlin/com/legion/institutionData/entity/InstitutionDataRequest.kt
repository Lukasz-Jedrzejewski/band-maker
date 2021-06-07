package com.legion.institutionData.entity

data class InstitutionDataRequest (
        val name: String,
        val city: String,
        val street: String,
        val streetNumber: String,
        val localNumber: String?,
        val phoneNumber: String?,
        val description: String
)