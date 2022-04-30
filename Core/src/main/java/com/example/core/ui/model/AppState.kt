package com.example.core.ui.model

sealed class AppState {
    object Loading : AppState()
    data class Success(val data: List<UiWord>?) : AppState()
    data class Error(val error: Throwable) : AppState()
}

