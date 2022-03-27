package com.example.mylexicon.datasource

import com.example.mylexicon.model.Word
import io.reactivex.rxjava3.core.Observable

class LocalDataSource: DataSource<List<Word>> {

    override fun getData(word: String): Observable<List<Word>> {
        TODO("Not yet implemented")
    }
}