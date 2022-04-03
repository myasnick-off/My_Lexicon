package com.example.mylexicon.di.module

import com.example.mylexicon.datasource.DataSource
import com.example.mylexicon.datasource.db.LocalDataSource
import com.example.mylexicon.datasource.network.ApiService
import com.example.mylexicon.datasource.network.RemoteDataSource
import com.example.mylexicon.model.Word
import com.example.mylexicon.repository.IRepository
import com.example.mylexicon.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(
        @Named(NAME_REMOTE) remoteSource: DataSource<List<Word>>,
        @Named(NAME_LOCAL) localSource: DataSource<List<Word>>
    ): IRepository<List<Word>> {
        return Repository(remoteSource, localSource)
    }

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    fun provideRemoteDataSource(apiService: ApiService): DataSource<List<Word>> {
       return RemoteDataSource(apiService)
    }

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideLocalDataSource(): DataSource<List<Word>> {
        return LocalDataSource()
    }

    companion object {
        private const val NAME_REMOTE = "Remote"
        private const val NAME_LOCAL = "Local"
    }
}


