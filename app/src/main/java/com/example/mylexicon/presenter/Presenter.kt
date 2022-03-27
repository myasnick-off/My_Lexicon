package com.example.mylexicon.presenter

import com.example.mylexicon.model.AppState
import com.example.mylexicon.ui.base.View

interface Presenter<T : AppState, V : View> {

    fun attachView(view: View)
    fun detachView(view: View)
    fun getData(word: String, isOnline: Boolean)
}