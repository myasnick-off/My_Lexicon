package com.example.mylexicon.repository

import com.example.core.model.Word
import com.example.mylexicon.datasource.db.LocalDataSource

class LocalRepository(private val localSource: LocalDataSource): IRepository<List<Word>>, ILocalRepo {

    override suspend fun getData(word: String): List<Word> {
        return localSource.getData(word)
    }

    override suspend fun saveData(words: List<Word>) {
        localSource.saveData(words)
    }

    override suspend fun loadData(): List<Word> {
        return localSource.loadData()
    }

}