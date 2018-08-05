package com.example.mohammed.movieappmvpkotlin.view

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.mohammed.movieappmvpkotlin.model.Movie
import kotlinx.android.synthetic.main.row_layout.view.*

open class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    var rawLayout: Int = 0
    lateinit var movies: List<Movie>
    lateinit var context: Context

    constructor(rawLayout: Int, movies: List<Movie>, context: Context) : super() {
        this.rawLayout = rawLayout
        this.movies = movies
        this.context = context
    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MovieViewHolder {
        val view: View = LayoutInflater.from(p0.context).inflate(rawLayout, p0, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(p0: MovieViewHolder, p1: Int) {
        p0.title.setText(movies.get(p1).title)
        p0.date.setText(movies.get(p1).release_date)
        p0.description.setText(movies.get(p1).overview)

        Glide.with(context).load("http://image.tmdb.org/t/p/w185//" + movies[p1].poster_path).into(p0.moviePoster)

        p0.layout.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                var intent: Intent = Intent(p0!!.getContext(), MovieDetails::class.java)
                intent.putExtra("movieTitle", movies[p1].title)
                intent.putExtra("movieDate", movies[p1].release_date)
                intent.putExtra("movieOverview", movies[p1].overview)
                intent.putExtra("posterImage", movies[p1].poster_path)
                p0.getContext().startActivity(intent)
            }
        })


    }

    open class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.title
        var date: TextView = itemView.date
        var description: TextView = itemView.description
        var moviePoster: ImageView = itemView.movie_poster
        var layout: LinearLayout = itemView.linear_layout


    }
}