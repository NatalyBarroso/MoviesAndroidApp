package com.example.moviesapp.views

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.adapters.MovieAdapter
import com.example.moviesapp.adapters.WatchlistAdapter
import com.example.moviesapp.databinding.ActivityWatchlistBinding
import com.example.moviesapp.models.WatchlistModel
import com.example.moviesapp.viewmodels.MovieViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects

class WatchlistActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWatchlistBinding
    private lateinit var adapter: WatchlistAdapter
    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWatchlistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        adapter = WatchlistAdapter(this, mutableListOf())
        binding.watchlistRecycleView.adapter = adapter
        binding.watchlistRecycleView.layoutManager = LinearLayoutManager(this)

    }

    override fun onResume() {
        super.onResume()
        loadWatchlist()
    }

    private fun loadWatchlist() {
        val userId = auth.currentUser?.uid ?: return
        firestore.collection("users").document(userId).collection("watchlist")
            .get()
            .addOnSuccessListener { result ->
                val watchlist = mutableListOf<WatchlistModel>()
                for (document in result) {
                    val movie = document.toObject<WatchlistModel>()
                    watchlist.add(movie)
                }
                adapter.updateWatchlist(watchlist)
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Failed to retrieve watchlist: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
    }
}