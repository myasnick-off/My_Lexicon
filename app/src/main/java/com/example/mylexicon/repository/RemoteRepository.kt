package com.example.mylexicon.repository

import com.example.mylexicon.datasource.DataSource
import com.example.mylexicon.model.Word

class RemoteRepository(private val remoteSource: DataSource<List<Word>>) : IRepository<List<Word>> {

    override suspend fun getData(word: String): List<Word> {
        return remoteSource.getData(word)
    }
}