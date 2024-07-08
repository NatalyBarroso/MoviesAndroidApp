package com.example.moviesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesapp.databinding.ActivityWatchlistDetailBinding
class WatchlistDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWatchlistDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWatchlistDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}