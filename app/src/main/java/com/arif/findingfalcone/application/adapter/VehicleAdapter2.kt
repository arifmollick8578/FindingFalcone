package com.arif.findingfalcone.application.adapter

import android.view.View
import android.widget.TextView
import com.arif.findingfalcone.R
import com.arif.findingfalcone.data.datamodels.responsemodels.VehicleResponse
import com.arif.findingfalcone.domain.dataclasses.Vehicle

class VehicleAdapter2(private val items: List<Vehicle>, private val onItemClicked: (Vehicle) -> Boolean): BaseAdapter<Vehicle>(ArrayList(items)) {
    override fun bindView(view: View, position: Int) {
        val itemName = items[position].name
        with(view) {
            findViewById<TextView>(R.id.title).text = itemName
            findViewById<TextView>(R.id.speed).text = items[position].speed.toString()
            findViewById<TextView>(R.id.max_distance).text = items[position].maxDistance.toString()
            findViewById<TextView>(R.id.unit).text = items[position].units.toString()

            view.isEnabled = !items[position].isSelected

            setOnClickListener {
                if (onItemClicked(items[position]) && !items[position].isSelected) {
                    isSelected = !isSelected
                }
            }
        }

//        view.findViewById<TextView>(R.id.title).text = itemName
//        view.findViewById<TextView>(R.id.speed).text = items[position].speed.toString()
//        view.findViewById<TextView>(R.id.max_distance).text = items[position].max_distance.toString()
//        view.findViewById<TextView>(R.id.unit).text = items[position].total_no.toString()
//
//        view.setOnClickListener {
//            onItemSelected(itemName)
//        }
    }

    override fun getLayoutId() = R.layout.vehicle_item
    override fun areContentsTheSame(oldItem: Vehicle, newItem: Vehicle): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: Vehicle, newItem: Vehicle): Boolean {
        return oldItem == newItem
    }

    override fun getItemCount() = items.size
}