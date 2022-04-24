package com.example.mylexicon.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Translation(
    @SerializedName("text") val text: String?,
    @SerializedName("note") val note: String?
) : Parcelable
