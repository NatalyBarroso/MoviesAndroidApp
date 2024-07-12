package com.example.moviesapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.core.Constants
import com.example.moviesapp.models.MovieModel
import com.example.moviesapp.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieViewModel: ViewModel() {

    private var _movieList = MutableLiveData<List<MovieModel>>()
    val movieList: LiveData<List<MovieModel>> = _movieList

    private var _genresList = MutableLiveData<Map<Int, String>>()
    val genresList: LiveData<Map<Int, String>> = _genresList

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
}