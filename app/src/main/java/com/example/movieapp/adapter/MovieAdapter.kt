package com.example.movieapp.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.config.EndPoints
import com.example.movieapp.model.Movie

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.HolderData> {
    var data : List<Movie> = ArrayList<Movie>()
    lateinit var ctx : Context
    lateinit var act : Activity

    constructor(data: List<Movie>, ctx: Context) : super() {
        this.data = data
        this.ctx = ctx
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderData {
        var v : View = LayoutInflater.from(ctx).inflate(R.layout.list_movie, parent, false)
        var holder : HolderData = HolderData(v)
        return holder
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: HolderData, position: Int) {
        var mov : Movie = data.get(position)
        mov.run {
            with(holder){
                txtTitle.setText(original_title)
                txtTgl.setText(release_date)
                txtDeskripsi.setText(overview)
                Glide.with(ctx).load("https://image.tmdb.org/t/p/w500/"+backdrop_path).into(poster)
            }
        }
    }

    inner class HolderData(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var txtTitle : TextView
        lateinit var txtTgl : TextView
        lateinit var txtDeskripsi : TextView
        lateinit var poster : ImageView
        init{
            itemView.run{
                txtTitle = findViewById(R.id.txt_judul)
                txtTgl= findViewById(R.id.txt_release)
                txtDeskripsi = findViewById(R.id.txt_deskripsi)
                poster = findViewById(R.id.imageView)
            }
        }
    }
}