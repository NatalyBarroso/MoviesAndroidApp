package com.example.moviesapp.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.adapters.WatchlistCardAdapter
import com.example.moviesapp.databinding.ActivityWatchlistBinding
import com.example.moviesapp.models.WatchlistItem

class WatchlistActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWatchlistBinding
    private lateinit var adapter: WatchlistCardAdapter
    private lateinit var watchlistItemList: MutableList<WatchlistItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWatchlistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.watchlistRecycleView.layoutManager = LinearLayoutManager(this)

        watchlistItemList = mutableListOf(
            WatchlistItem("Title 1", "Genre 1"),
            WatchlistItem("Title 2", "Genre 2")
        )

        adapter = WatchlistCardAdapter(watchlistItemList)
        binding.watchlistRecycleView.adapter = adapter

    }
}