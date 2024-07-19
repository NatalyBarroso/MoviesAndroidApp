package com.example.moviesapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviesapp.R
import com.example.moviesapp.core.Constants
import com.example.moviesapp.models.MovieModel
import com.example.moviesapp.models.WatchlistModel
import com.example.moviesapp.viewmodels.MovieViewModel
import com.example.moviesapp.views.MovieDetailActivity

class MovieAdapter(val context: Context, var moviesList: List<MovieModel>, val viewModel: MovieViewModel): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val movieCardView: CardView = itemView.findViewById(R.id.movieCardView)
        val poster: ImageView = itemView.findViewById(R.id.posterImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_movies, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
    return moviesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = moviesList[position]

        Glide
            .with(context)
            .load("${Constants.BASE_URL_IMAGE}${movie.poster}")
            .apply(RequestOptions().override(Constants.IMAGE_WEIGHT, Constants.IMAGE_HEIGHT))
            .into(holder.poster)

        holder.movieCardView.setOnClickListener {
            val intent = Intent(holder.itemView.context, MovieDetailActivity::class.java).apply {
                putExtra("MOVIE_ID", movie.id)
                putExtra("MOVIE_TITLE", movie.title)
                putExtra("MOVIE_POSTER_URL", movie.poster)
                putExtra("MOVIE_DESCRIPTION", movie.synipsis)
                putExtra("MOVIE_GENRE", viewModel.getGenreNames(movie.genres).toTypedArray())
                putExtra("DELETE_BUTTON", false)
            }
            holder.itemView.context.startActivity(intent)
        }

    }

    fun updateMovies(newMovieList: List<MovieModel>) {
        moviesList = newMovieList
        notifyDataSetChanged()
    }

}