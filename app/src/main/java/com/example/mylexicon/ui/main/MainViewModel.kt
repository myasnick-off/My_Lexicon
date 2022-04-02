package com.example.mylexicon.ui.main

import com.example.mylexicon.datasource.db.LocalDataSource
import com.example.mylexicon.datasource.network.RemoteDataSource
import com.example.mylexicon.interactor.MainInteractor
import com.example.mylexicon.model.AppState
import com.example.mylexicon.repository.Repository
import com.example.mylexicon.ui.base.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(
    private val interactor: MainInteractor =
        MainInteractor(Repository(RemoteDataSource(), LocalDataSource()))
): BaseViewModel<AppState>() {

    override fun getData(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    mutableLiveData.value = AppState.Loading
                }
                .subscribe(
                    { appState ->
                        mutableLiveData.postValue(appState)
                    },
                    { ex ->
                        mutableLiveData.postValue(AppState.Error(ex))
                    }
                )
        )
    }

}