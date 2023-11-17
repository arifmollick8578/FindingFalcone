package com.arif.findingfalcone.data.repository.datasourceimpl

import com.arif.findingfalcone.data.GeektrustService
import com.arif.findingfalcone.data.datamodels.requestmodels.FindFalconRequestBody
import com.arif.findingfalcone.data.repository.datasource.FindFalconRemoteDataSource
import com.arif.findingfalcone.data.utils.NetworkManager.performNetworkOperation

class FindFalconRemoteDataSourceImpl(private val service: GeektrustService): FindFalconRemoteDataSource {
    override suspend fun getVehicles() = performNetworkOperation {
        service.getVehicles()
    }

    override suspend fun getPlanets() = performNetworkOperation {
        service.getPlanets()
    }

    override suspend fun getToken() = performNetworkOperation {
        service.getToken()
    }

    override suspend fun getResult(requestModel: FindFalconRequestBody) = performNetworkOperation {
        println("FATAL:: request model: $requestModel")
        service.findFalconApi(requestModel)
    }
}
