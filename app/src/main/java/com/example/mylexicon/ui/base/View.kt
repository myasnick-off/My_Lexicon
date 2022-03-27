package com.example.mylexicon.ui.base

import com.example.mylexicon.model.AppState

interface View {
    fun renderData(state: AppState)
}