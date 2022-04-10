package com.example.mylexicon.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Meaning2(
    @SerializedName("id") val id: Int,
    @SerializedName("translation") val translation:  Translation?,
    @SerializedName("imageUrl") val imageUrl: String?,
    @SerializedName("transcription") val transcription: String?,
    @SerializedName("soundUrl") val soundUrl: String?
):Parcelable