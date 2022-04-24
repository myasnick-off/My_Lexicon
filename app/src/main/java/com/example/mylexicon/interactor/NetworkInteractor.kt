package com.example.mylexicon.interactor

import com.example.core.model.AppState
import com.example.mylexicon.repository.LocalRepository
import com.example.mylexicon.repository.RemoteRepository

class NetworkInteractor(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository
) : INetworkInteractor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return try {
            val data = if (fromRemoteSource) remoteRepository.getData(word) else localRepository.getData(word)
            if (data.isEmpty()) {
                return AppState.Error(Throwable(EMPTY_DATA_MESSAGE))
            }
            if (fromRemoteSource) localRepository.saveData(data)
            AppState.Success(data)
        } catch (ex: Exception) {
            AppState.Error(ex)
        }
    }

    companion object {
        private const val EMPTY_DATA_MESSAGE = "Nothing found!"
    }
}