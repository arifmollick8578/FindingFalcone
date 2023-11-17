package com.arif.findingfalcone.domain.dataclasses

import com.arif.findingfalcone.data.datamodels.responsemodels.PlanetResponse

data class Planet(
    val name: String,
    val distance: Int,
    var isSelected: Boolean = false
)

fun PlanetResponse.toPlanet(isSelected: Boolean = false): Planet {
    return Planet(
        name = name,
        distance = distance,
        isSelected = isSelected
    )
}