package com.example.mylexicon.di.module

import com.example.mylexicon.interactor.Interactor
import com.example.mylexicon.interactor.MainInteractor
import com.example.mylexicon.model.AppState
import com.example.mylexicon.model.Word
import com.example.mylexicon.repository.IRepository
import dagger.Module
import dagger.Provides

@Module
class InteractorModule {

    @Provides
    fun provideInteractor(repository: IRepository<List<Word>>): Interactor<AppState> {
       return MainInteractor(repository)
    }
}
