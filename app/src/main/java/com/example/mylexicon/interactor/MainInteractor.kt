package com.example.mylexicon.interactor

import com.example.mylexicon.model.AppState
import com.example.mylexicon.model.Word
import com.example.mylexicon.repository.IRepository

class MainInteractor(private val repository: IRepository<List<Word>>) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(repository.getData(word, fromRemoteSource))
    }
}