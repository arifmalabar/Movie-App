package com.example.movieapp

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.movieapp.API.APIMovie
import com.example.movieapp.adapter.MovieAdapter
import com.example.movieapp.config.Koneksi
import com.example.movieapp.model.Movie
import com.example.movieapp.response.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var rv_data : RecyclerView
    lateinit var rv_adapter : RecyclerView.Adapter<*>
    lateinit var layout : RecyclerView.LayoutManager
    lateinit var pb : ProgressBar
    lateinit var refresh : SwipeRefreshLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRefresh()
        setData()
    }

    private fun setRefresh() {
        refresh = findViewById(R.id.refresh_layout)
        refresh.setOnRefreshListener {
            setData()
        }
    }

    private fun setData() {
        pb = findViewById(R.id.progressBar)
        pb.visibility = View.VISIBLE
        rv_data = findViewById(R.id.movie_list)
        var api: APIMovie = Koneksi.getKoneksi().create(APIMovie::class.java)
        var data: Call<MovieResponse> = api.getData()
        data.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                when (response.code()) {
                    200 -> {
                        Toast.makeText(applicationContext, "Tes", Toast.LENGTH_LONG).show()
                        pb.visibility = View.VISIBLE
                        layout = GridLayoutManager(applicationContext, 2)
                        var data: List<Movie> = response.body()!!.results
                        with(rv_data) {
                            rv_adapter = MovieAdapter(data, applicationContext)
                            adapter = rv_adapter
                            layoutManager = layout
                        }
                        pb.visibility = View.GONE
                    }
                    else -> {
                        Toast.makeText(
                            applicationContext,
                            "Internal Server Error",
                            Toast.LENGTH_LONG
                        ).show()
                        pb.visibility = View.VISIBLE
                    }
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                pb.visibility = View.VISIBLE
            }

        })
    }
}