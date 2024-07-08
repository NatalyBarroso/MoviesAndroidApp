package com.example.moviesapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.adapters.WatchedCardAdapter
import com.example.moviesapp.databinding.ActivityWatchedBinding
import com.example.moviesapp.models.WatchedItem

class WatchedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWatchedBinding
    private lateinit var adapter: WatchedCardAdapter
    private lateinit var watchedItemList: MutableList<WatchedItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWatchedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.watchedRecycleView.layoutManager = LinearLayoutManager(this)

        watchedItemList = mutableListOf(
            WatchedItem("Title 1", "Genre 1"),
            WatchedItem("Title 2", "Genre 2")
        )

        adapter = WatchedCardAdapter(watchedItemList)
        binding.watchedRecycleView.adapter = adapter



    }
}