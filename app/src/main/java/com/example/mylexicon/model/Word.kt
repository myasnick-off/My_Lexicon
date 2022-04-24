package com.example.mylexicon.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Word(
    @SerializedName("id") val id: Int,
    @SerializedName("text") val text: String?,
    @SerializedName("meanings") val meanings: List<Meaning2>?
): Parcelable
