package com.example.mylexicon.di

import com.example.mylexicon.datasource.DataSource
import com.example.mylexicon.datasource.db.LocalDataSource
import com.example.mylexicon.datasource.network.RemoteDataSource
import com.example.mylexicon.interactor.Interactor
import com.example.mylexicon.interactor.MainInteractor
import com.example.mylexicon.model.AppState
import com.example.mylexicon.model.Word
import com.example.mylexicon.repository.IRepository
import com.example.mylexicon.repository.Repository
import com.example.mylexicon.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

object DI {
    private const val NAME_REMOTE = "Remote"
    private const val NAME_LOCAL = "Local"

    val appModule = module {
        single<DataSource<List<Word>>>(named(NAME_LOCAL)) { LocalDataSource() }
        single<DataSource<List<Word>>>(named(NAME_REMOTE)) { RemoteDataSource() }
        single<IRepository<List<Word>>> {
            Repository(remoteSource = get(named(NAME_REMOTE)), localSource = get(named(NAME_LOCAL)))
        }
    }

    val mainModule = module {
        factory<Interactor<AppState>> { MainInteractor(repository = get()) }
        viewModel { MainViewModel(interactor = get()) }
    }
}