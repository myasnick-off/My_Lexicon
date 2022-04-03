package com.example.mylexicon.repository

interface IRepository<T> {
    suspend fun getData(word: String, fromRemote: Boolean): T
}