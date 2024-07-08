package com.example.moviesapp.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.R
import com.example.moviesapp.WatchlistDetailActivity
import com.example.moviesapp.models.WatchlistItem

class WatchlistCardAdapter(private var watchlistItemlist: List<WatchlistItem>) : RecyclerView.Adapter<WatchlistCardAdapter.WatchlistCardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchlistCardAdapter.WatchlistCardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.watched_item, parent, false)
        return WatchlistCardViewHolder(view)
    }

    override fun onBindViewHolder(holder: WatchlistCardAdapter.WatchlistCardViewHolder, position: Int) {
        val item = watchlistItemlist[position]
        holder.titleTextView.text = item.title
        holder.genreTextView.text = item.genre

        holder.cardView.setOnClickListener {
            val intent = Intent(holder.itemView.context, WatchlistDetailActivity::class.java)
            intent.putExtra("title", item.title)
            intent.putExtra("genre", item.genre)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return watchlistItemlist.size
    }

    class WatchlistCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.title_textview)
        val genreTextView: TextView = itemView.findViewById(R.id.genre_textview)
        val cardView: CardView = itemView.findViewById(R.id.movieCardView)
    }
}