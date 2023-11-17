package com.arif.findingfalcone.domain.dataclasses

import com.arif.findingfalcone.data.datamodels.responsemodels.VehicleResponse

data class Vehicle(
    val name: String,
    val units: Int,
    val maxDistance: Int,
    val speed: Int,
    var isSelected: Boolean
)

fun VehicleResponse.toVehicle(isSelected: Boolean = false): Vehicle {
    return Vehicle(
        name = name,
        units = total_no,
        maxDistance = max_distance,
        speed = speed,
        isSelected = isSelected
    )
}