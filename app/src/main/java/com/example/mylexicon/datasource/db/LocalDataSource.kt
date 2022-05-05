package com.example.mylexicon.datasource.db

import com.example.core.model.Word
import com.example.mylexicon.datasource.DataSource
import com.example.mylexicon.datasource.LocalSource
import com.example.mylexicon.datasource.db.dao.WordDao
import com.example.mylexicon.utils.ModelsMapper

class LocalDataSource(private val wordDao: WordDao, private val mapper: ModelsMapper) : DataSource<List<Word>>, LocalSource {

    override suspend fun saveData(words: List<Word>) {
        wordDao.insertAll(words.map { word -> mapper.wordModelToEntityConvert(word) })
    }

    override suspend fun loadData(): List<Word> {
        return wordDao.getAll().map { entity -> mapper.wordEntityToModelConvert(entity) }
    }

    override suspend fun getData(word: String): List<Word> {
        return wordDao.findAll(word).map { entity -> mapper.wordEntityToModelConvert(entity) }
    }
}