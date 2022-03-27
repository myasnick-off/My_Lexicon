package com.example.mylexicon.presenter

import com.example.mylexicon.model.AppState
import com.example.mylexicon.ui.base.View

interface IPresenter<T : AppState, V : View> {

    fun attachView(view: V)
    fun detachView(view: V)
    fun getData(word: String, isOnline: Boolean)
}