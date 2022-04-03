package com.example.mylexicon.ui.main

import com.example.mylexicon.interactor.Interactor
import com.example.mylexicon.model.AppState
import com.example.mylexicon.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val interactor: Interactor<AppState>) : BaseViewModel<AppState>() {

    override fun getData(word: String, isOnline: Boolean) {
        mutableLiveData.value = AppState.Loading
        viewModelScope.cancel()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val appState = (interactor.getData(word, isOnline)) as AppState.Success
                if (appState.data == null || appState.data.isEmpty()) {
                    mutableLiveData.postValue(AppState.Error(Throwable(ERROR_MESSAGE)))
                } else {
                    mutableLiveData.postValue(appState)
                }
            }
        }
    }

    override fun handleException(ex: Throwable) {
        mutableLiveData.postValue(AppState.Error(ex))
    }

    companion object {
        private const val ERROR_MESSAGE = "Empty data!"
    }
}