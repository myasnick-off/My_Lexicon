package com.example.mylexicon.ui.main

import com.example.mylexicon.interactor.INetworkInteractor
import com.example.core.model.AppState
import com.example.core.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val interactor: INetworkInteractor<AppState>) : BaseViewModel<AppState>() {

    override fun getData(word: String, isOnline: Boolean) {
        job?.cancel()
        job = viewModelScope.launch {
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