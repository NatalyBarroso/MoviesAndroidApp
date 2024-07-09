package com.example.moviesapp.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.R
import com.example.moviesapp.views.WatchedDetailActivity
import com.example.moviesapp.models.WatchedItem

class WatchedCardAdapter(private val watchedItemList: List<WatchedItem>) : RecyclerView.Adapter<WatchedCardAdapter.WatchedCardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchedCardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.watched_item, parent, false)
        return WatchedCardViewHolder(view)
    }

    override fun onBindViewHolder(holder: WatchedCardViewHolder, position: Int) {
        val item = watchedItemList[position]
        holder.titleTextView.text = item.title
        holder.genreTextView.text = item.genre

        holder.cardView.setOnClickListener {
            val intent = Intent(holder.itemView.context, WatchedDetailActivity::class.java)
            intent.putExtra("title", item.title)
            intent.putExtra("genre", item.genre)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return watchedItemList.size
    }

    class WatchedCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.title_textview)
        val genreTextView: TextView = itemView.findViewById(R.id.genre_textview)
        val cardView: CardView = itemView.findViewById(R.id.movieCardView)
    }

}