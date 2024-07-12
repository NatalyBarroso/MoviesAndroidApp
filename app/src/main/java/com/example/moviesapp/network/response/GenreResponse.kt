package com.example.moviesapp.network.response

import com.example.moviesapp.models.GenreModel
import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @SerializedName("genres")
    val genres: List<GenreModel>
)


