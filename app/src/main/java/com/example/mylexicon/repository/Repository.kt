package com.example.mylexicon.repository

import com.example.mylexicon.datasource.DataSource
import com.example.mylexicon.model.Word
import io.reactivex.rxjava3.core.Single

class Repository(
    private val remoteSource: DataSource<List<Word>>,
    private val localSource: DataSource<List<Word>>
): IRepository<List<Word>> {

    override suspend fun getData(word: String, fromRemote: Boolean): List<Word> {
        return if(fromRemote) remoteSource.getData(word) else localSource.getData(word)
    }
}