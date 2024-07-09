package com.example.moviesapp.models

import com.google.gson.annotations.SerializedName

data class MovieModel(
    @SerializedName("id")
    var id: String,
    @SerializedName("original_title")
    var title: String,
    @SerializedName("overview")
    var synipsis: String,
    @SerializedName("poster_path")
    var poster: String,
    @SerializedName("vote_average")
    var averageVote: String
)
