package com.example.mylexicon.model

sealed class AppState {
    data class Loading(val progress: Int?) : AppState()
    data class Success(val data: List<Word>?) : AppState()
    data class Error(val error: Throwable) : AppState()
}

