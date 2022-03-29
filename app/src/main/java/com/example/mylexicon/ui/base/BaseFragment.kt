package com.example.mylexicon.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.mylexicon.model.AppState

abstract class BaseFragment<T: AppState>: Fragment(), BaseView {

    protected lateinit var presenter: IPresenter<T, BaseView>

    protected abstract fun createPresenter(): IPresenter<T, BaseView>
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