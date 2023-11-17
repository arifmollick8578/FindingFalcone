package com.arif.findingfalcone.data.utils

import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.lang.Exception

object NetworkManager {

    suspend fun <T> performNetworkOperation(operation: suspend () -> Response<T>) = flow {
        try {
            emit(NetworkStatus.Loading)
            val request = operation()
            println("FATAL:: request: $request")
            if (request.isSuccessful && request.body() != null) {
                emit(NetworkStatus.Success(request.body()!!))
            } else {
                emit(NetworkStatus.Failed(request.errorBody().toString()))
            }
        } catch (e: AccessDeniedException) {
            emit(NetworkStatus.Exception("Access Denied for the API execution.", e))
        } catch (e: Exception) {
            emit(NetworkStatus.Exception(e.message ?: "Something went wrong!", e))
        }
    }
}