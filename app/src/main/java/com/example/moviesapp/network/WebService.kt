package com.example.moviesapp.network

import com.example.moviesapp.network.response.GenreResponse
import com.example.moviesapp.network.response.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    //@GET("movie/now_playing")
    @GET("discover/movie")
    suspend fun getMovies(
        @Query("api_key") apiKey: String
    ): Response<MovieResponse>

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String
    ): Response<MovieResponse >

    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") apiKey: String
    ): Response<GenreResponse>

    @GET("search/movie")
    suspend fun getSearchedMovie(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): Response<MovieResponse>
}