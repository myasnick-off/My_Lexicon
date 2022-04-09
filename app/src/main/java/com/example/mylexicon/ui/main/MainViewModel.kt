package com.example.mylexicon.ui.main

import com.example.mylexicon.interactor.Interactor
import com.example.mylexicon.model.AppState
import com.example.mylexicon.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val interactor: Interactor<AppState>) : BaseViewModel<AppState>() {

    override fun getData(word: String, isOnline: Boolean) {
        viewModelScope.launch {
            mutableLiveData.value = AppState.Loading

            val appState = withContext(Dispatchers.IO) {
                 interactor.getData(word, isOnline)
            }
            mutableLiveData.value = appState
        }
    }

    override fun handleException(ex: Throwable) {
        mutableLiveData.postValue(AppState.Error(ex))
    }
}