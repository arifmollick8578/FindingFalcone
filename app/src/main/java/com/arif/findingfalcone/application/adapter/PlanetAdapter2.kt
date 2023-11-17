package com.arif.findingfalcone.application.adapter

import android.media.Image.Plane
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.DrawableUtils
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.arif.findingfalcone.R
import com.arif.findingfalcone.domain.dataclasses.Planet

class PlanetAdapter2(private val items: List<Planet>, private val onItemClicked: (Planet) -> Boolean): BaseAdapter<Planet>(
    ArrayList(items)
) {
    private var lastSelection: View? = null
    private var currentSelection: View? = null

    override fun bindView(view: View, position: Int) {
        println("FATAL:: bindView called")
        view.findViewById<TextView>(R.id.title).text = items[position].name
        view.findViewById<TextView>(R.id.distance).text = items[position].distance.toString()

        view.isEnabled = !items[position].isSelected

        view.setOnClickListener {
            bindView(view, position)
            if (onItemClicked(items[position])  && !items[position].isSelected) {
                lastSelection?.let { it.isSelected != it.isSelected }
                lastSelection = currentSelection
                view.isSelected = !view.isSelected
                currentSelection = view
            }
        }
    }


//    override fun onBindViewHolder(
//        holder: RecyclerView.ViewHolder,
//        position: Int,
//        payloads: MutableList<Any>
//    ) {
//        println("FATAL:: payload called")
//        when (val payload = payloads.lastOrNull()) {
//            (payload == Payloads.IS_SELECTED) -> {
//                holder.itemView.findViewById<TextView>(R.id.selected).text = if (items[position].isSelected) "Selected" else "Unselected"
//            }
//            else -> { super.onBindViewHolder(holder, position) }
//        }
//    }


    override fun getLayoutId() = R.layout.planet_item
    override fun areContentsTheSame(oldItem: Planet, newItem: Planet): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: Planet, newItem: Planet): Boolean {
        return oldItem == newItem
    }

    override fun getItemCount() = items.size

//    override fun getChangePayload(oldItem: Planet, newItem: Planet): Any? {
//        return if (oldItem.isSelected != newItem.isSelected && oldItem == newItem) {
//            Payloads.IS_SELECTED
//        } else {
//            super.getChangePayload(oldItem, newItem)
//        }
//    }
}

enum class Payloads {
    IS_SELECTED
}
