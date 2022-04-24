package com.example.mylexicon.repository

import com.example.mylexicon.datasource.network.RemoteDataSource
import com.example.core.model.Word

class RemoteRepository(private val remoteSource: RemoteDataSource) : IRepository<List<Word>> {

    override suspend fun getData(word: String): List<Word> {
        return remoteSource.getData(word)
    }
}