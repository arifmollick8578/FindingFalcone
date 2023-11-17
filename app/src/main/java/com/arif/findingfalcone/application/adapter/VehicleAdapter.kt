package com.arif.findingfalcone.application.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arif.findingfalcone.R
import com.arif.findingfalcone.data.MockData
import com.arif.findingfalcone.data.datamodels.responsemodels.VehicleResponse

class VehicleAdapter(private val item: List<VehicleResponse>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//    private lateinit var binding: VehicleItemBinding
    private lateinit var view: View
    val selectedItems = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        view = LayoutInflater.from(parent.context).inflate(R.layout.vehicle_item, parent, false)

//        binding = VehicleItemBinding.bind(parent)
        return object: RecyclerView.ViewHolder(view) {}
    }

    override fun getItemCount() = item.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        view.findViewById<TextView>(R.id.title).text = item[position].name
        view.findViewById<TextView>(R.id.speed).text = item[position].speed.toString()
        view.findViewById<TextView>(R.id.max_distance).text = item[position].max_distance.toString()
        view.findViewById<TextView>(R.id.unit).text = item[position].total_no.toString()

        view.setOnClickListener {
            if (selectedItems.contains(item[position].name)) {
                selectedItems.remove(item[position].name)
                view.background = view.resources.getDrawable(R.color.white)
            } else {
                selectedItems.add(item[position].name)
                view.background = view.resources.getDrawable(androidx.appcompat.R.color.material_blue_grey_800)
            }
        }

//        binding.title.text = item[position].title
//        binding.speed.text = item[position].speed
//        binding.maxDistance.text = item[position].distance
//        binding.unit.text = item[position].units.toString()
    }
}