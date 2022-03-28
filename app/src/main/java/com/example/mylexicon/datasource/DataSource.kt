package com.example.mylexicon.datasource

import io.reactivex.rxjava3.core.Single

interface DataSource<T> {
    fun getData(word: String): Single<T>
}