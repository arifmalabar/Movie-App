package com.example.movieapp.config

import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Koneksi {
    fun getKoneksi() : Retrofit{
        val retrofit : Retrofit = Retrofit.Builder().baseUrl(EndPoints.baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
        return retrofit
    }
}