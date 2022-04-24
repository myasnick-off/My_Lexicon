package com.example.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.model.AppState
import kotlinx.coroutines.*

abstract class BaseViewModel<T : AppState> : ViewModel() {

    protected val mutableLiveData: MutableLiveData<T> = MutableLiveData()
    val liveData: LiveData<T>
        get() = mutableLiveData

    protected var job: Job? = null
    protected val viewModelScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, ex -> handleException(ex) }
    )

    abstract fun handleException(ex: Throwable)

    abstract fun getData(word: String, isOnline: Boolean)

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}