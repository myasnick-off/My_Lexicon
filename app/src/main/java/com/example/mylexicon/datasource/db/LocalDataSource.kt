package com.example.mylexicon.datasource.db

import com.example.mylexicon.datasource.DataSource
import com.example.mylexicon.model.Word

class LocalDataSource: DataSource<List<Word>> {

    override suspend fun getData(word: String): List<Word> {
        TODO("Not yet implemented")
    }
}