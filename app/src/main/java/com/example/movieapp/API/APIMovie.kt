package com.example.movieapp.API

import com.example.movieapp.response.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface APIMovie {
    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwZGYxMDQ0NjkwZmY2Nzc1ZDM0MTVmNWQ0MWM5NmI1ZSIsInN1YiI6IjY0NmIzNTU4MzNhMzc2MDE1OGRhYzAxNiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Y_a7W9UVcnXSa5yEGn_DRBCMY8YZhzPM1asV6EzPbfY")
    @GET("movie/now_playing")
    fun getData() : Call<MovieResponse>

}