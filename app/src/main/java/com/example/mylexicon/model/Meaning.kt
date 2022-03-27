package com.example.mylexicon.model

import com.google.gson.annotations.Expose


data class Meaning(
    @Expose val id: String,
    @Expose val difficultyLevel: Int,
    @Expose val text: String?,
    @Expose val soundUrl: String?,
    @Expose val transcription: String?,
    @Expose val translation: Translation?,
    @Expose val images: List<Image>?
)