package com.example.mylexicon.ui.main

import com.example.mylexicon.datasource.db.LocalDataSource
import com.example.mylexicon.datasource.network.RemoteDataSource
import com.example.mylexicon.interactor.MainInteractor
import com.example.mylexicon.model.AppState
import com.example.mylexicon.repository.Repository
import com.example.mylexicon.ui.base.IPresenter
import com.example.mylexicon.ui.base.View
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainPresenter<T : AppState, V : View>(
    private val interactor: MainInteractor =
        MainInteractor(Repository(RemoteDataSource(), LocalDataSource())),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : IPresenter<T, V> {

    private var mainView: V? = null

    override fun attachView(view: V) {
        if (view != mainView) {
            mainView = view
        }
    }

    override fun detachView(view: V) {
        compositeDisposable.clear()
        if (view == mainView) {
            mainView = null
        }
    }

    override fun getData(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .doOnSubscribe {
                    mainView?.renderData(AppState.Loading) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        mainView?.renderData(it)
                    },
                    {
                        mainView?.renderData(AppState.Error(it))
                    }
                )
        )
    }

}