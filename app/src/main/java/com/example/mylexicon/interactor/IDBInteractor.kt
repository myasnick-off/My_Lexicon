package com.example.mylexicon.interactor

interface IDBInteractor<T> {
    suspend fun getData(word: String): T
    suspend fun loadData(): T
}