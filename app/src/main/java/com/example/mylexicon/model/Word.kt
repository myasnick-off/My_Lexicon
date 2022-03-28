package com.example.mylexicon.model

data class Word(
    val id: Int,
    val text: String?,
    val meanings: List<Meaning>?
)
