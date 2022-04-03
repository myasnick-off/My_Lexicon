package com.example.mylexicon.interactor

import io.reactivex.rxjava3.core.Single


interface Interactor<T> {
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}