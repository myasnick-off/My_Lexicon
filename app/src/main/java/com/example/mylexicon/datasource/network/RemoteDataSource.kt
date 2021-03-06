package com.example.mylexicon.datasource.network

import com.example.core.model.Word
import com.example.mylexicon.datasource.DataSource
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource : DataSource<List<Word>> {

    private val apiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
        .build()
    }

    override suspend fun getData(word: String): List<Word> {
        return apiService.searchAsync(word).await()
    }

    companion object {
        private const val BASE_URL = "https://dictionary.skyeng.ru/api/public/v1/"
    }
}