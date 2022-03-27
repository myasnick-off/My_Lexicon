package com.example.mylexicon.ui

import com.example.mylexicon.model.AppState

interface View {
    fun renderData(state: AppState)
}