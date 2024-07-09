package com.example.moviesapp.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesapp.databinding.ActivityWatchedDetailBinding

class WatchedDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWatchedDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWatchedDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}