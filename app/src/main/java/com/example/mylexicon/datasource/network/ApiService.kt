package com.example.mylexicon.datasource.network

import com.example.mylexicon.model.Word
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("words/search")
    fun search(
        @Query("search") word: String
    ): Single<List<Word>>
}