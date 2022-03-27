package com.example.mylexicon.repository

import io.reactivex.rxjava3.core.Observable

interface IRepository<T> {
    fun getData(word: String, fromRemote: Boolean): Observable<T>
}