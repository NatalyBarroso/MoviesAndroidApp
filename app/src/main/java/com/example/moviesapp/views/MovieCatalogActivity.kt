package com.example.moviesapp.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import com.example.moviesapp.adapters.MovieAdapter
import com.example.moviesapp.databinding.ActivityMovieCatalogBinding
import com.example.moviesapp.viewmodels.MovieViewModel
import com.example.moviesapp.models.MovieModel

class MovieCatalogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieCatalogBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var adapter: MovieAdapter
    private var moviesList: List<MovieModel> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieCatalogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                if(!query.isNullOrEmpty()) {
                    viewModel.searchMovie(query)
                }
                return true
            }
        })

        viewModel.movieList.observe(this, Observer { movies ->
            adapter.updateMovies(movies)
        })

        setupRecyclerView()

        viewModel.movieList.observe(this) {
            moviesList = it
            adapter.moviesList = it
            adapter.notifyDataSetChanged()
        }

        viewModel.getMovies()
    }

    private fun setupRecyclerView() {
        val layoutManager = GridLayoutManager(this, 3)
        binding.movieRecycleView.layoutManager = layoutManager
        adapter = MovieAdapter(this, arrayListOf(), viewModel)
        binding.movieRecycleView.adapter = adapter
    }
}
