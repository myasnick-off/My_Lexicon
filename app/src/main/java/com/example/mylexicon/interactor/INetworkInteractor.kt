package com.example.mylexicon.interactor

interface INetworkInteractor<T> {
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}