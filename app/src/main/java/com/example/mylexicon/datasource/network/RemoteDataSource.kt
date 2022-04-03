package com.example.mylexicon.datasource.network

import com.example.mylexicon.datasource.DataSource
import com.example.mylexicon.model.Word
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource : DataSource<List<Word>> {

    private val apiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor())
        .build()
    }

    override fun getData(word: String): Single<List<Word>> {
        return apiService.search(word)
    }

    companion object {
        private const val BASE_URL = "https://dictionary.skyeng.ru/api/public/v1/"
    }
}