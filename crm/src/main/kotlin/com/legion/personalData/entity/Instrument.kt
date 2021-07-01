package com.legion.personalData.entity

enum class Instrument(private val instrumentName: String) {
    ELECTRIC_GUITAR("gitara elektryczna"),
    BASS_GUITAR("gitara basowa"),
    CLASSIC_GUITAR("gitara klasyczna"),
    ACOUSTIC_GUITAR("gitara klasyczna"),
    DRUMS("perkusja");

    override fun toString(): String {
        return instrumentName;
    }


}