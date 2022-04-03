package com.example.mylexicon.ui.base

import androidx.fragment.app.Fragment
import com.example.mylexicon.model.AppState

abstract class BaseFragment<T: AppState>: Fragment() {

    abstract val viewModel: BaseViewModel<T>

    abstract fun renderData(appState: T)
}