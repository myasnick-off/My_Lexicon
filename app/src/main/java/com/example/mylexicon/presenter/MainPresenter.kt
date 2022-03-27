package com.example.mylexicon.presenter

import com.example.mylexicon.datasource.db.LocalDataSource
import com.example.mylexicon.datasource.network.RemoteDataSource
import com.example.mylexicon.interactor.MainInteractor
import com.example.mylexicon.model.AppState
import com.example.mylexicon.repository.Repository
import com.example.mylexicon.ui.base.View
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class MainPresenter<T:AppState, V:View>(
    private val interactor: MainInteractor =
        MainInteractor(Repository(RemoteDataSource(), LocalDataSource())),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()
): IPresenter<T, V> {

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
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .doOnSubscribe{ mainView?.renderData(AppState.Loading(null)) }
                .subscribeWith(getObserver())
        )
    }

    private fun getObserver() : DisposableObserver<AppState> {
        return object :DisposableObserver<AppState>() {

            override fun onNext(state: AppState) {
                mainView?.renderData(state)
            }

            override fun onError(e: Throwable) {
                mainView?.renderData(AppState.Error(e))
            }

            override fun onComplete() {}
        }
    }

}