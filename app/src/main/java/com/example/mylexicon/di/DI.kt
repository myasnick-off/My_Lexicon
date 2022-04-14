package com.example.mylexicon.di

import androidx.room.Room
import com.example.mylexicon.datasource.DataSource
import com.example.mylexicon.datasource.db.LexiconDatabase
import com.example.mylexicon.datasource.db.LocalDataSource
import com.example.mylexicon.datasource.network.RemoteDataSource
import com.example.mylexicon.interactor.Interactor
import com.example.mylexicon.interactor.MainInteractor
import com.example.mylexicon.model.AppState
import com.example.mylexicon.model.Word
import com.example.mylexicon.repository.IRepository
import com.example.mylexicon.repository.LocalRepository
import com.example.mylexicon.repository.RemoteRepository
import com.example.mylexicon.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

object DI {
    private const val NAME_REMOTE = "Remote"
    private const val NAME_LOCAL = "Local"
    private const val DB_NAME = "database.db"

    val appModule = module {
        single { Room.databaseBuilder(get(), LexiconDatabase::class.java, DB_NAME).build() }
        single { get<LexiconDatabase>().wordDao() }
        single<DataSource<List<Word>>>(named(NAME_LOCAL)) { LocalDataSource(get()) }
        single<DataSource<List<Word>>>(named(NAME_REMOTE)) { RemoteDataSource() }
        single<IRepository<List<Word>>> { RemoteRepository(remoteSource = get(named(NAME_REMOTE))) }
        single<IRepository<List<Word>>> { LocalRepository(localSource = get(named(NAME_LOCAL))) }
    }

    val mainModule = module {
        factory<Interactor<AppState>> { MainInteractor(remoteRepository = get(), localRepository = get()) }
        viewModel { MainViewModel(interactor = get()) }
    }
}