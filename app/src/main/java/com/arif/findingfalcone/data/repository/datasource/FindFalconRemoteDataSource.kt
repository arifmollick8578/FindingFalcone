package com.arif.findingfalcone.data.repository.datasource

import com.arif.findingfalcone.data.datamodels.requestmodels.FindFalconRequestBody
import com.arif.findingfalcone.data.utils.NetworkStatus
import kotlinx.coroutines.flow.Flow

interface FindFalconRemoteDataSource {
    suspend fun getVehicles(): Flow<NetworkStatus>

    suspend fun getPlanets(): Flow<NetworkStatus>

    suspend fun getToken(): Flow<NetworkStatus>

    suspend fun getResult(requestModel: FindFalconRequestBody): Flow<NetworkStatus>
}
