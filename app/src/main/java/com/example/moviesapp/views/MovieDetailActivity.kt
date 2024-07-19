package com.example.moviesapp.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.moviesapp.core.Constants
import com.example.moviesapp.databinding.ActivityMovieDetailBinding
import com.example.moviesapp.models.MovieModel
import com.example.moviesapp.models.WatchedModel
import com.example.moviesapp.models.WatchlistModel
import com.example.moviesapp.viewmodels.MovieViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    private lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        val id = intent.getStringExtra("MOVIE_ID")
        val title = intent.getStringExtra("MOVIE_TITLE")
        val poster = intent.getStringExtra("MOVIE_POSTER_URL")
        val synopsis = intent.getStringExtra("MOVIE_DESCRIPTION")
        val genre = intent.getStringArrayExtra("MOVIE_GENRE")
        val deleteBtn = intent.getBooleanExtra("DELETE_BUTTON", true)
        val addBtn = intent.getBooleanExtra("ADD_BUTTON", true)


        if (deleteBtn) {
            binding.deleteButton.visibility = View.VISIBLE
        } else {
            binding.deleteButton.visibility = View.GONE
        }

        if (addBtn) {
            binding.watchlistButton.visibility =View.VISIBLE
        } else {
            binding.watchlistButton.visibility =View.GONE
        }

        binding.titleTextView.text = title
        binding.genreTextView.text = genre?.joinToString(", ")
        binding.synopsisEditText.setText(synopsis)
        Glide.with(this).load("${Constants.BASE_URL_IMAGE}${poster}").into(binding.moviePoster)

        binding.watchlistButton.setOnClickListener {
            val watchlistMovie = WatchlistModel(
                id = id ?: "",
                title = title ?: "",
                poster = poster ?: "",
                synopsis = synopsis ?: "",
                genre = genre?.joinToString(", ") ?: "",
            )
            saveToWatchlist(watchlistMovie)
        }

        binding.watchedButton.setOnClickListener {
            val intent = Intent(this, WatchedDetailActivity::class.java).apply {
                putExtra("MOVIE_ID", id)
                putExtra("MOVIE_TITLE", title)
                putExtra("MOVIE_DESCRIPTION", synopsis)
                putExtra("MOVIE_GENRE", genre)
                putExtra("MOVIE_POSTER_URL", poster)
            }
            startActivity(intent)
        }

        binding.deleteButton.setOnClickListener {
            id?.let { deleteFromWatchlist(it) }
        }

    }

    private fun saveToWatchlist(watchlistMovie: WatchlistModel) {
        val userId = auth.currentUser?.uid ?: return
        firestore.collection("users").document(userId).collection("watchlist")
            .add(watchlistMovie)
            .addOnSuccessListener {
                Toast.makeText(this, "Movie added to watchlist", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Failed to add movie: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun deleteFromWatchlist(movieID: String) {
        val userID = auth.currentUser?.uid ?: return
        val watchlistRef = firestore
            .collection("users")
            .document(userID)
            .collection("watchlist")

        watchlistRef.whereEqualTo("id", movieID).get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    watchlistRef.document(document.id).delete()
                        .addOnSuccessListener {
                            Toast.makeText(this, "Movie removed from watchlist", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                        .addOnFailureListener { error ->
                            Toast.makeText(this, "Failed to remove movie: ${error.message}", Toast.LENGTH_SHORT).show()
                        }
                }
            }
            .addOnFailureListener { error ->
                Toast.makeText(this, "Failed to find movie: ${error.message}", Toast.LENGTH_SHORT).show()
            }
    }

}