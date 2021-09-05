package com.celestial.uiprototype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CarRecyAdapter(): RecyclerView.Adapter<CarRecyAdapter.CarRVViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarRVViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rcy_item_layout,parent,false)
        return CarRVViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarRVViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
       return 8
    }

    inner class CarRVViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}