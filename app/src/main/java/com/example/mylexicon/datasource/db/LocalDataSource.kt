package com.example.mylexicon.datasource.db

import com.example.mylexicon.datasource.DataSource
import com.example.mylexicon.datasource.LocalSource
import com.example.mylexicon.datasource.db.dao.WordDao
import com.example.mylexicon.model.Word
import com.example.mylexicon.utils.wordEntityToModelConvert
import com.example.mylexicon.utils.wordModelToEntityConvert

class LocalDataSource(private val wordDao: WordDao) : DataSource<List<Word>>, LocalSource {

    override suspend fun saveData(words: List<Word>) {
        wordDao.insertAll(words.map { word -> wordModelToEntityConvert(word) })
    }

    override suspend fun loadData(): List<Word> {
        return wordDao.getAll().map { entity -> wordEntityToModelConvert(entity) }
    }

    override suspend fun getData(word: String): List<Word> {
        return wordDao.findAll(word).map { entity -> wordEntityToModelConvert(entity) }
    }
}