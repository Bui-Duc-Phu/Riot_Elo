package com.example.riot_elo.MyBase

fun toRomanNumber(input: String): String {
    return when (input) {
        "1" -> "I"
        "2" -> "II"
        "3" -> "III"
        "4" -> "IV"
        else -> "Invalid"
    }
}
