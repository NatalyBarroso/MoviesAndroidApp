package com.example.moviesapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.core.Constants
import com.example.moviesapp.models.MovieModel
import com.example.moviesapp.models.WatchlistModel
import com.example.moviesapp.network.RetrofitClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieViewModel: ViewModel() {

    private var _movieList = MutableLiveData<List<MovieModel>>()
    val movieList: LiveData<List<MovieModel>> = _movieList

    private var _genresList = MutableLiveData<Map<Int, String>>()
    val genresList: LiveData<Map<Int, String>> = _genresList

    private var _watchlist = MutableLiveData<List<WatchlistModel>>()
    val watchlist: LiveData<List<WatchlistModel>> = _watchlist

    init {
        getGenres()
    }

    fun getMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.webService.getMovies(Constants.API_KEY)
            withContext(Dispatchers.Main) {
                _movieList.value = response.body()!!.results.sortedByDescending { it.averageVote }
            }
        }
    }

    fun getPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.webService.getPopularMovies(Constants.API_KEY)
            withContext(Dispatchers.Main) {
               // _movieList.value = response.body()!!.results.sortedByDescending { it.averageVote }
                val top5Movies = response.body()!!.results.sortedByDescending { it.averageVote }.take(5)
                _movieList.value = top5Movies

            }
        }
    }

    private fun getGenres() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.webService.getGenres(Constants.API_KEY)
            withContext(Dispatchers.Main) {
                _genresList.value = response.body()?.genres?.associate { it.id to it.name } ?: emptyMap()
            }
        }
    }

    fun getGenreNames(ids: List<Int>): List<String> {
        val genresMap = _genresList.value ?: return emptyList()
        return ids.mapNotNull {  genresMap[it] }
    }

    fun searchMovie(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.webService.getSearchedMovie(Constants.API_KEY, query)
            withContext(Dispatchers.Main) {
                _movieList.value = response.body()?.results?.sortedByDescending { it.averageVote }
            }
        }
    }
}