package com.arif.findingfalcone.data

object MockData {

    data class DataHolder(
        val title: String,
        val distance: String,
        val speed: String = "",
        val units: Int = 0
    )

    fun getPlanets(): List<DataHolder> {
        val item = mutableListOf<DataHolder>()
        for (i in 0..6) {
            item.add(DataHolder("Title: $i", "Distance: $i", "Speed: $i", i))
        }
        return item
    }
}