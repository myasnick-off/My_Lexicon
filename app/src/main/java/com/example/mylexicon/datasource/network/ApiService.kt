package com.example.mylexicon.datasource.network

import com.example.mylexicon.model.Word
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(SEARCH_ENDPOINT)
    fun search(
        @Query("search") word: String
    ): Single<List<Word>>

    companion object {
        private const val SEARCH_ENDPOINT = "words/search"
    }
}