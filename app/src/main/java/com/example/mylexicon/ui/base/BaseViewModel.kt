package com.example.mylexicon.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mylexicon.model.AppState
import kotlinx.coroutines.*

abstract class BaseViewModel<T : AppState>(
    protected val mutableLiveData: MutableLiveData<T> = MutableLiveData(),
) : ViewModel() {

    val liveData: LiveData<T>
        get() = mutableLiveData

    protected val viewModelScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, ex -> handleException(ex) }
    )

    abstract fun handleException(ex: Throwable)

    abstract fun getData(word: String, isOnline: Boolean)

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}