package com.example.mylexicon.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.mylexicon.model.AppState
import com.example.mylexicon.presenter.Presenter

abstract class BaseFragment<T: AppState>: Fragment(), View {

    protected lateinit var presenter: Presenter<T, View>

    protected abstract fun createPresenter(): Presenter<T, View>
    abstract override fun renderData(state: AppState)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }
}