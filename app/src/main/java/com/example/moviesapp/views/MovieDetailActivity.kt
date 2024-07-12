package com.example.moviesapp.views

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.moviesapp.core.Constants
import com.example.moviesapp.databinding.ActivityMovieDetailBinding

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val title = intent.getStringExtra("MOVIE_TITLE")
        val poster = intent.getStringExtra("MOVIE_POSTER_URL")
        val synopsis = intent.getStringExtra("MOVIE_DESCRIPTION")
        val genre = intent.getStringArrayExtra("MOVIE_GENRE")
        val isVisible = intent.getStringExtra("BTN_ISVISBLE").toBoolean()

        if (isVisible) {
            binding.deleteButton.visibility = View.VISIBLE
        } else {
            binding.deleteButton.visibility = View.INVISIBLE
        }

        binding.titleTextView.text = title
        binding.genreTextView.text = genre?.joinToString(", ")
        binding.synopsisEditText.setText(synopsis)
        Glide.with(this).load("${Constants.BASE_URL_IMAGE}${poster}").into(binding.moviePoster)


    }
}