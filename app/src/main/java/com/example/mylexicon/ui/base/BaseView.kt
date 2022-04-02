package com.example.mylexicon.ui.base

import com.example.mylexicon.model.AppState

interface BaseView {
    fun renderData(state: AppState)
}