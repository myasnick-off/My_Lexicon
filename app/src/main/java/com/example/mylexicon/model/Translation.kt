package com.example.mylexicon.model

import com.google.gson.annotations.Expose

data class Translation(
    @Expose val text: String?,
    @Expose val note: String?
)
