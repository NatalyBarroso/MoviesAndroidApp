package com.example.moviesapp.views

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.adapters.MovieAdapter
import com.example.moviesapp.databinding.ActivityMovieCatalogBinding
import com.example.moviesapp.viewmodels.MovieViewModel

class MovieCatalogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieCatalogBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieCatalogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]

        setupRecyclerView()

        viewModel.movieList.observe(this) {
            adapter.moviesList = it
            adapter.notifyDataSetChanged()
        }

        binding.catalogbtn.setOnClickListener {
            viewModel.getMovies()
            changeColorbtn("catalog")
        }

        binding.popularbtn.setOnClickListener {
            viewModel.getPopularMovies()
            changeColorbtn("popular")
        }

        viewModel.getMovies()

    }

    private fun changeColorbtn(btn: String) {
        when(btn) {
            "catalog" -> {
                binding.catalogbtn.setCardBackgroundColor(resources.getColor(R.color.GulfStream))
                binding.popularbtn.setCardBackgroundColor(resources.getColor(R.color.Cyprus))
            }

            "popular" -> {
                binding.catalogbtn.setCardBackgroundColor(resources.getColor(R.color.Cyprus))
                binding.catalogbtn.setCardBackgroundColor(resources.getColor(R.color.GulfStream))
            }
        }
    }

    private fun setupRecyclerView() {
        val layoutManager = GridLayoutManager(this, 3)
        binding.movieRecycleView.layoutManager = layoutManager
        adapter = MovieAdapter(this, arrayListOf(), viewModel, false)
        binding.movieRecycleView.adapter = adapter
        }
    }
