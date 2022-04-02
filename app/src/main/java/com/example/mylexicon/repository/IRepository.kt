package com.example.mylexicon.repository

import io.reactivex.rxjava3.core.Single

interface IRepository<T> {
    fun getData(word: String, fromRemote: Boolean): Single<T>
}