package com.example.mylexicon.datasource

import com.example.core.model.Word

interface DataSource<T> {
    suspend fun getData(word: String): List<Word>
}