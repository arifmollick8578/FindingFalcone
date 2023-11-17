package com.arif.findingfalcone.data.utils

sealed class NetworkStatus {

    data class Success(val data: Any): NetworkStatus()

    data class Failed(val message: String, val errorCode: Int? = null): NetworkStatus()

    data class Exception(val message: String, val exception:Throwable? = null): NetworkStatus()

    object Loading : NetworkStatus()
}
