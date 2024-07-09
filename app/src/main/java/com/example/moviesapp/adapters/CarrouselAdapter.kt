package com.example.moviesapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.R
import com.example.moviesapp.models.CarrouselItem

class CarrouselAdapter: ListAdapter<CarrouselItem, CarrouselAdapter.ViewHolder>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<CarrouselItem>(){
        override fun areItemsTheSame(oldItem: CarrouselItem, newItem: CarrouselItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CarrouselItem, newItem: CarrouselItem): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(iteView: View): RecyclerView.ViewHolder(iteView){
        private val imageView = itemView.findViewById<ImageView>(R.id.imageView)

        fun bindData(item: CarrouselItem){
            Glide.with(itemView)
                .load(item.url)
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarrouselAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.image_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val carrouselItem = getItem(position)
        holder.bindData(carrouselItem)
    }

}