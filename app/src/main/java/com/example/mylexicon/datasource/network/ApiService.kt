package com.example.mylexicon.datasource.network

import com.example.mylexicon.model.Word
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(SEARCH_ENDPOINT)
    fun searchAsync(
        @Query("search") word: String
    ): Deferred<List<Word>>

    companion object {
        private const val SEARCH_ENDPOINT = "words/search"
    }
}