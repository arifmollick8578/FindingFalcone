package com.arif.findingfalcone.data.repository

import com.arif.findingfalcone.data.datamodels.requestmodels.FindFalconRequestBody
import com.arif.findingfalcone.data.repository.datasource.FindFalconRemoteDataSource
import com.arif.findingfalcone.domain.repository.FindFalconRepository

class FindFalconRepositoryImpl(private val dataSource: FindFalconRemoteDataSource): FindFalconRepository {
    override suspend fun getVehicles() = dataSource.getVehicles()

    override suspend fun getPlanets() = dataSource.getPlanets()

    override suspend fun getToken() = dataSource.getToken()

    override suspend fun getResult(requestModel: FindFalconRequestBody) = dataSource.getResult(requestModel)
}
