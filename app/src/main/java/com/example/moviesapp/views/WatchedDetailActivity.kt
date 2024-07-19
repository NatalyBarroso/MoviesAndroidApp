package com.example.moviesapp.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.moviesapp.core.Constants
import com.example.moviesapp.databinding.ActivityWatchedDetailBinding
import com.example.moviesapp.models.WatchedModel
import com.example.moviesapp.viewmodels.MovieViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class WatchedDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWatchedDetailBinding
    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    private lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWatchedDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        val id = intent.getStringExtra("MOVIE_ID")
        val title = intent.getStringExtra("MOVIE_TITLE")
        val poster = intent.getStringExtra("MOVIE_POSTER_URL")
        val synopsis = intent.getStringExtra("MOVIE_DESCRIPTION")
        val genre = intent.getStringArrayExtra("MOVIE_GENRE")
        //val review = binding.reviewEditText.text
        //val raiting = 10

        binding.titleTextView.text = title
        binding.genreTextView.text = genre?.joinToString(", ")
        binding.synopsisEditText.setText(synopsis)
        Glide.with(this).load("${Constants.BASE_URL_IMAGE}${poster}").into(binding.moviePoster)


    }
}