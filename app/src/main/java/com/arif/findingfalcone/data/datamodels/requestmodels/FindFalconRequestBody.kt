package com.arif.findingfalcone.data.datamodels.requestmodels

data class FindFalconRequestBody(
    val token: String,
    val planet_names: List<String>,
    val vehicle_names: List<String>
)
