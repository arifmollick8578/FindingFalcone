package com.arif.findingfalcone.application.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.arif.findingfalcone.R
import com.arif.findingfalcone.application.adapter.PlanetAdapter2
import com.arif.findingfalcone.application.adapter.VehicleAdapter2
import com.arif.findingfalcone.domain.dataclasses.Planet
import com.arif.findingfalcone.domain.dataclasses.Vehicle

class SelectionLayoutSection(
    private val context: Context,
//    private val planets: List<Planet>,
//    private val vehicles: List<Vehicle>,
//    private val planetSelection:(Planet) -> Boolean,
//    private val vehicleSelection: (Vehicle) -> Boolean
): ConstraintLayout(context) {

//    private var view: View

    init {
        inflate(context, R.layout.selection_layout, this)
//        view = LayoutInflater.from(context).inflate(R.layout.selection_layout, null)

//        view.findViewById<RecyclerView>(R.id.rv1).apply {
//            adapter = PlanetAdapter2(planets) {
//                planetSelection(it)
//            }
//        }

//        view.findViewById<RecyclerView>(R.id.rv2).apply {
//            adapter = VehicleAdapter2(vehicles) {
//                vehicleSelection(it)
//            }
//        }

//        this.addView(view)
    }

    fun addPlanets(planet: List<Planet>, planetSelection:(Planet) -> Boolean) {
        findViewById<RecyclerView>(R.id.rv1).apply {
            adapter = PlanetAdapter2(planet) {
                planetSelection(it)
            }
        }
    }

    fun addVehicles(vehicle: List<Vehicle>, vehicleSelection:(Vehicle) -> Boolean) {
        findViewById<RecyclerView>(R.id.rv2).apply {
            adapter = VehicleAdapter2(vehicle) {
                vehicleSelection(it)
            }
        }
    }

//    fun addActionButtonListener(listener: (View) -> Unit) {
//        findViewById<Button>(R.id.next_button).setOnClickListener(listener)
//    }

//    fun disableButton() {
//        findViewById<Button>(R.id.next_button).isEnabled = false
//    }

//    fun addActionButtonText(text: String) {
//        findViewById<Button>(R.id.next_button).text = text
//    }


}