package com.example.mylexicon.model

sealed class AppState {
    object Loading : AppState()
    data class Success(val data: List<Word>?) : AppState()
    data class Error(val error: Throwable) : AppState()
}

