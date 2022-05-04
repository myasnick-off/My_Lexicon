package com.example.mylexicon.di

import androidx.room.Room
import com.example.core.ui.model.AppState
import com.example.mylexicon.datasource.db.LexiconDatabase
import com.example.mylexicon.datasource.db.LocalDataSource
import com.example.mylexicon.datasource.network.RemoteDataSource
import com.example.mylexicon.interactor.DBInteractor
import com.example.mylexicon.interactor.IDBInteractor
import com.example.mylexicon.interactor.INetworkInteractor
import com.example.mylexicon.interactor.NetworkInteractor
import com.example.mylexicon.repository.LocalRepository
import com.example.mylexicon.repository.RemoteRepository
import com.example.mylexicon.ui.history.HistoryFragment
import com.example.mylexicon.ui.history.HistoryViewModel
import com.example.mylexicon.ui.main.MainFragment
import com.example.mylexicon.ui.main.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

object DI {
    private const val DB_NAME = "database.db"

    val appModule = module {
        single { Room.databaseBuilder(androidApplication(), LexiconDatabase::class.java, DB_NAME).build() }
        single { get<LexiconDatabase>().wordDao() }
        single { LocalDataSource(wordDao = get()) }
        single { RemoteDataSource() }
        single { LocalRepository(localSource = get()) }
        single { RemoteRepository(remoteSource = get()) }
    }

    val mainModule = module {
        scope(named<MainFragment>()) {
            scoped<INetworkInteractor<AppState>> { NetworkInteractor(remoteRepository = get(), localRepository = get()) }
            viewModel { MainViewModel(interactor = get()) }
        }
    }

    val historyModule = module {
        scope(named<HistoryFragment>()) {
            scoped<IDBInteractor<AppState>> { DBInteractor(localRepository = get()) }
            viewModel { HistoryViewModel(interactor = get()) }
        }
    }

    val widgetModule = module {
        factory<IDBInteractor<AppState>> { DBInteractor(localRepository = get()) }
    }
}