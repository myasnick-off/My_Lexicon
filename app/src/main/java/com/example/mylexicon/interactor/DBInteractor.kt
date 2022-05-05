package com.example.mylexicon.interactor

import com.example.core.ui.model.AppState
import com.example.mylexicon.repository.LocalRepository
import com.example.mylexicon.utils.ModelsMapper

class DBInteractor(
    private val localRepository: LocalRepository,
    private val mapper: ModelsMapper
) : IDBInteractor<AppState> {

    override suspend fun getData(word: String): AppState {
        return try {
            val data = localRepository.getData(word)
            if (data.isEmpty()) {
                return AppState.Error(Throwable(EMPTY_DATA_MESSAGE))
            }
            AppState.Success(data.map { mapper.wordModelToUiConvert(it) })
        } catch (ex: Exception) {
            AppState.Error(ex)
        }
    }

    override suspend fun loadData(): AppState {
        return try {
            val data = localRepository.loadData()
            if (data.isEmpty()) {
                return AppState.Error(Throwable(EMPTY_DATA_MESSAGE))
            }
            AppState.Success(data.map { mapper.wordModelToUiConvert(it) })
        } catch (ex: Exception) {
            AppState.Error(ex)
        }
    }

    companion object {
        private const val EMPTY_DATA_MESSAGE = "History is empty!"
    }

}