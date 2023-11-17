package com.arif.findingfalcone.domain.repository

import com.arif.findingfalcone.data.datamodels.requestmodels.FindFalconRequestBody
import com.arif.findingfalcone.data.utils.NetworkStatus
import kotlinx.coroutines.flow.Flow

interface FindFalconRepository {

    suspend fun getVehicles(): Flow<NetworkStatus>

    suspend fun getPlanets(): Flow<NetworkStatus>

    suspend fun getToken(): Flow<NetworkStatus>

    suspend fun getResult(requestModel: FindFalconRequestBody): Flow<NetworkStatus>
}
