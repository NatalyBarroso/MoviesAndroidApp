package com.example.moviesapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.R
import com.example.moviesapp.core.Constants
import com.example.moviesapp.models.WatchlistModel
import com.example.moviesapp.views.MovieDetailActivity
import com.example.moviesapp.views.WatchlistActivity

class WatchlistAdapter(private val context: Context, private var watchlist: List<WatchlistModel>) : RecyclerView.Adapter<WatchlistAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieCardView: CardView = itemView.findViewById(R.id.watchlistCardView)
        val poster: ImageView = itemView.findViewById(R.id.moviePoster)
        val title: TextView = itemView.findViewById(R.id.title_textview)
        val genre: TextView = itemView.findViewById(R.id.genre_textview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_watchlist, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return watchlist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = watchlist[position]

        Glide.with(context)
            .load("${Constants.BASE_URL_IMAGE}${movie.poster}")
            .into(holder.poster)
        holder.title.text = movie.title
        holder.genre.text = movie.genre

        holder.movieCardView.setOnClickListener {
           val intent = Intent(holder.itemView.context, MovieDetailActivity::class.java).apply {
               putExtra("MOVIE_ID", movie.id)
               putExtra("MOVIE_TITLE", movie.title)
               putExtra("MOVIE_DESCRIPTION", movie.synopsis)
               putExtra("MOVIE_GENRE", movie.genre)
               putExtra("MOVIE_POSTER_URL", movie.poster)
               putExtra("ADD_BUTTON", false)
           }
            holder.itemView.context.startActivity(intent)
        }
    }

    fun updateWatchlist(newWatchlist: List<WatchlistModel>) {
        watchlist = newWatchlist
        notifyDataSetChanged()
    }
}
