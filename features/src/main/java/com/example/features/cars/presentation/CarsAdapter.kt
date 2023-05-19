package com.example.features.cars.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.room.data.Cars
import com.example.features.R
import com.example.features.databinding.CarItemBinding
import java.text.SimpleDateFormat
import java.util.*

class CarsAdapter(val onClick: (Cars) -> Unit) :
    ListAdapter<Cars, CarsAdapter.Holder>(
        Comparator()
    ) {

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = CarItemBinding.bind(itemView)

        fun bind(item: Cars) = with(binding) {
            nameCar.text = item.name
            yearRelease.text = item.year.toString()
            engineCapacity.text = item.engine.toString()
            date.text = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(item.date)
            Glide.with(itemView)
                .load(item.image)
                .into(imageCar)
        }

        init {
            itemView.setOnClickListener {
                onClick(getItem(adapterPosition))
            }
        }

    }

    class Comparator : DiffUtil.ItemCallback<Cars>() {
        override fun areItemsTheSame(oldItem: Cars, newItem: Cars): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Cars, newItem: Cars): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.car_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

}