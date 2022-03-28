package com.example.mylexicon.interactor

import io.reactivex.rxjava3.core.Single


interface Interactor<T> {
    fun getData(word: String, fromRemoteSource: Boolean): Single<T>
}