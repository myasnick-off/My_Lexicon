package com.example.mylexicon.model


data class Meaning(
    val id: String,
    val difficultyLevel: Int,
    val text: String?,
    val soundUrl: String?,
    val transcription: String?,
    val translation: Translation?,
    val images: List<Image>?
)