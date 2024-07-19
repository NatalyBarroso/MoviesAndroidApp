package com.example.moviesapp.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.databinding.ActivityWatchedBinding

class WatchedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWatchedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWatchedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.watchedRecycleView.layoutManager = LinearLayoutManager(this)



    }
}