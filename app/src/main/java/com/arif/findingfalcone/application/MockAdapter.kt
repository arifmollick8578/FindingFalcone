package com.arif.findingfalcone.application

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arif.findingfalcone.R
import com.arif.findingfalcone.data.MockData

class MockAdapter(private val item: List<MockData.DataHolder>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//    private lateinit var binding: VehicleItemBinding
    private lateinit var view: View

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        view = LayoutInflater.from(parent.context).inflate(R.layout.vehicle_item, parent, false)

//        binding = VehicleItemBinding.bind(parent)
        return object: RecyclerView.ViewHolder(view) {}
    }

    override fun getItemCount() = item.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        view.findViewById<TextView>(R.id.title).text = item[position].title
        view.findViewById<TextView>(R.id.speed).text = item[position].speed.toString()
        view.findViewById<TextView>(R.id.max_distance).text = item[position].distance.toString()
        view.findViewById<TextView>(R.id.unit).text = item[position].units.toString()

//        binding.title.text = item[position].title
//        binding.speed.text = item[position].speed
//        binding.maxDistance.text = item[position].distance
//        binding.unit.text = item[position].units.toString()
    }
}