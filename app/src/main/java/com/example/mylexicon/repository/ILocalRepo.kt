package com.example.mylexicon.repository

import com.example.mylexicon.model.Word

interface ILocalRepo {
    suspend fun saveData(words: List<Word>)
    suspend fun loadData(): List<Word>
}