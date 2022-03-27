package com.example.mylexicon.datasource.network

import com.example.mylexicon.datasource.DataSource
import com.example.mylexicon.model.Word
import io.reactivex.rxjava3.core.Observable

class RemoteDataSource(private val retrofitImpl: RetrofitImpl = RetrofitImpl()) :
    DataSource<List<Word>> {

    override fun getData(word: String): Observable<List<Word>> {
        return retrofitImpl.getData(word)
    }
}