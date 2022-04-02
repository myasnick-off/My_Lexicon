package com.example.mylexicon.datasource.network

import com.example.mylexicon.datasource.DataSource
import com.example.mylexicon.model.Word
import io.reactivex.rxjava3.core.Single
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource(private val apiService: ApiService) : DataSource<List<Word>> {

    override fun getData(word: String): Single<List<Word>> {
        return apiService.search(word)
    }
}