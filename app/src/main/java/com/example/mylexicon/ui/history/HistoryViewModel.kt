package com.example.mylexicon.ui.history

import com.example.core.ui.base.BaseViewModel
import com.example.core.ui.model.AppState
import com.example.mylexicon.interactor.IDBInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryViewModel(private val interactor: IDBInteractor<AppState>) : BaseViewModel<AppState>() {

    init {
        loadData()
    }

    override fun getData(word: String, isOnline: Boolean) {
        job?.cancel()
        job = viewModelScope.launch {
            mutableLiveData.value = AppState.Loading

            val appState = withContext(Dispatchers.IO) {
                interactor.getData(word)
            }
            mutableLiveData.value = appState
        }
    }

    fun loadData() {
        job?.cancel()
        job = viewModelScope.launch {
            mutableLiveData.value = AppState.Loading

            val appState = withContext(Dispatchers.IO) {
                interactor.loadData()
            }
            mutableLiveData.value = appState
        }
    }

    override fun handleException(ex: Throwable) {
        mutableLiveData.postValue(AppState.Error(ex))
    }
}