package com.example.mylexicon.datasource

import com.example.core.model.Word

interface LocalSource {
    suspend fun saveData(words: List<Word>)
    suspend fun loadData(): List<Word>
}