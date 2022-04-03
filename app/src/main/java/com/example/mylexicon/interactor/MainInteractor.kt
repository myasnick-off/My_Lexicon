package com.example.mylexicon.interactor

import com.example.mylexicon.model.AppState
import com.example.mylexicon.model.Word
import com.example.mylexicon.repository.IRepository
import io.reactivex.rxjava3.core.Single

class MainInteractor (private val repository: IRepository<List<Word>>): Interactor<AppState> {

    override fun getData(word: String, fromRemoteSource: Boolean): Single<AppState> {
        return repository.getData(word, fromRemoteSource)
            .map { words ->
                AppState.Success(words) }
    }
}