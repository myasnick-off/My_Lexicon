package com.example.mylexicon.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.mylexicon.model.AppState

abstract class BaseFragment<T: AppState>: Fragment(), View {

    protected lateinit var presenter: IPresenter<T, View>

    protected abstract fun createPresenter(): IPresenter<T, View>
    abstract override fun renderData(state: AppState)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    /*override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }*/

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView(this)
    }

   /* override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }*/
}