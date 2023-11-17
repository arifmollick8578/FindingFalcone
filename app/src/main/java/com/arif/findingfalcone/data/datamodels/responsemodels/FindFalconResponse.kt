package com.arif.findingfalcone.data.datamodels.responsemodels

sealed class FindFalconResponse(message: String) {
    data class FindFalconSuccessResponse(
        val planet_name: String,
        val status: String
    ): FindFalconResponse(planet_name)

    data class FindFalconFailureResponse(val status: String): FindFalconResponse(status)

    data class FindFalconExceptionResponse(val message: String): FindFalconResponse(message)
}
