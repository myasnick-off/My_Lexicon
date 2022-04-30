package com.example.core.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UiWord(
    val id: Int,
    val word: String,
    val translation: String,
    val note: String,
    val imageUrl: String?,
    val transcription: String
): Parcelable