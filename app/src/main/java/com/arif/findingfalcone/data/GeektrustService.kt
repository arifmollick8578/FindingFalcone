package com.arif.findingfalcone.data

import com.arif.findingfalcone.data.datamodels.requestmodels.FindFalconRequestBody
import com.arif.findingfalcone.data.datamodels.responsemodels.PlanetResponse
import com.arif.findingfalcone.data.datamodels.responsemodels.TokenResponse
import com.arif.findingfalcone.data.datamodels.responsemodels.VehicleResponse
import com.arif.findingfalcone.data.utils.Constants.BASE_URL
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface GeektrustService {

    @GET("/planets")
    suspend fun getPlanets(): Response<List<PlanetResponse>>

    @GET("/vehicles")
    suspend fun getVehicles(): Response<List<VehicleResponse>>

    @Headers(value = ["Accept:application/json"])
    @POST("/token")
    suspend fun getToken(): Response<TokenResponse>

    @Headers(value = ["Accept:application/json", "Content-Type:application/json"])
    @POST("/find")
    suspend fun findFalconApi(@Body requestBody: FindFalconRequestBody): Response<Any>
}

object RetrofitApi {
    fun getRetrofitApi(): GeektrustService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GeektrustService::class.java)
    }
 }

