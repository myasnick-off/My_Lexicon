package com.example.mylexicon.model

import com.google.gson.annotations.SerializedName


data class Meaning2(
    @SerializedName("id") val id: Int,
    @SerializedName("translation") val translation:  Translation?,
//    @SerializedName("imageUrl") val imageUrl: String?,
//    @SerializedName("transcription") val transcription: String?,
//    @SerializedName("soundUrl") val soundUrl: String?
)