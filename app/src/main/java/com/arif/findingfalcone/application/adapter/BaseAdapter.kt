package com.arif.findingfalcone.application.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T : Any>(private var items: ArrayList<T>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var view: View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        view = LayoutInflater.from(parent.context).inflate(getLayoutId(), parent, false)
        return object: RecyclerView.ViewHolder(view) {}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        bindView(view, position)
    }

    fun dispatchItems(newItem: List<T>) {
        val diffCallback = BaseDiffUtil(items, ArrayList(newItem))
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        items.clear()
        items.addAll(newItem)
        diffResult.dispatchUpdatesTo(this@BaseAdapter)
    }

    abstract fun bindView(view: View, position: Int)
    abstract fun  getLayoutId(): Int
    abstract fun areItemsTheSame(oldItem: T, newItem: T): Boolean
    abstract fun areContentsTheSame(oldItem: T, newItem: T): Boolean
    open fun getChangePayload(oldItem: T, newItem: T): Any? {
        return null
    }
    abstract override fun getItemCount(): Int

    protected inner class BaseDiffUtil constructor(private val oldItem: ArrayList<T>, private val newItem: ArrayList<T>): DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldItem.size
        }

        override fun getNewListSize(): Int {
            return newItem.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return this@BaseAdapter.areItemsTheSame(oldItem[oldItemPosition], newItem[newItemPosition]) && oldItemPosition == newItemPosition
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return this@BaseAdapter.areContentsTheSame(oldItem[oldItemPosition], newItem[newItemPosition]) && oldItemPosition == newItemPosition
        }

        override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
            return this@BaseAdapter.getChangePayload(oldItem[oldItemPosition], newItem[newItemPosition]) ?: super.getChangePayload(oldItemPosition, newItemPosition)
        }
    }
}