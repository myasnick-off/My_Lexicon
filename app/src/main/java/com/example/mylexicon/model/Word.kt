package com.example.mylexicon.model

import com.google.gson.annotations.Expose

data class Word(
    @Expose val id: Int,
    @Expose val text: String?,
    @Expose val meanings: List<Meaning>?
)
