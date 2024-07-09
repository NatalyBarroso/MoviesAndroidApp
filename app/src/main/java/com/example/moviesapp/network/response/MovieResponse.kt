package com.example.moviesapp.network.response

import com.example.moviesapp.models.MovieModel
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    var results: List<MovieModel>
)
