package com.example.movieapp.response
import com.example.movieapp.model.Movie

class MovieResponse {
    var dates : Map<String, String> = HashMap<String, String>()
    var page : Int = 0
    var results : List<Movie> = ArrayList<Movie>()
}