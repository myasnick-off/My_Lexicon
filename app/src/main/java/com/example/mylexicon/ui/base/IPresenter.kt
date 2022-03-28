package com.example.mylexicon.ui.base

import com.example.mylexicon.model.AppState

interface IPresenter<T : AppState, V : View> {

    fun attachView(view: V)
    fun detachView(view: V)
    fun getData(word: String, isOnline: Boolean)
}