package com.example.moviesapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.moviesapp.databinding.ActivityWatchedDetailBinding

class WatchedDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWatchedDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWatchedDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}