package com.example.mohammed.movieappmvpkotlin.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.mohammed.movieappmvpkotlin.R
import com.example.mohammed.movieappmvpkotlin.R.id.progress_bar_details
import com.example.mohammed.movieappmvpkotlin.model.Movie
import com.example.mohammed.movieappmvpkotlin.presenter.MoviePresenter
import kotlinx.android.synthetic.main.movie_details.*

class MovieDetails : AppCompatActivity(), IMain {
    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun updateView(moviesList: List<Movie>) {
    }

    override fun viewError(msg: String) {
        Toast.makeText(this, "" + msg, Toast.LENGTH_SHORT)
    }

    lateinit var progressBar: ProgressBar
    lateinit var detailsPresenter: MoviePresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_details)

        progressBar = progress_bar_details
        detailsPresenter = MoviePresenter(this)
        detailsPresenter.getDataFromModel()

        getIncomingIntent()


    }

    private fun getIncomingIntent() {

        if (intent.hasExtra("movieTitle")
                && intent.hasExtra("movieDate")
                && intent.hasExtra("movieOverview")
                && intent.hasExtra("posterImage")) {

            val movieTitle = intent.getStringExtra("movieTitle")

            val movieDate = intent.getStringExtra("movieDate")

            val movieOverview = intent.getStringExtra("movieOverview")

            val posterImage = intent.getStringExtra("posterImage")

            setDetails(posterImage, movieTitle, movieDate, movieOverview)
        }
    }


    private fun setDetails(imageUrl: String, movieTitle: String, movieDate: String, movieOverview: String) {

        val title = movie_title_details
        title.text = movieTitle

        val date = movie_date_details
        date.text = movieDate

        val overview = movie_overview_details
        overview.text = movieOverview


        val image = movie_poster_details
        Glide.with(this)
                .load("http://image.tmdb.org/t/p/w185//$imageUrl")
                .into(image)

        //        Glide.with(getApplicationContext()).load("http://image.tmdb.org/t/p/w185//" + movies.get().getPosterPath().toString()).into(image);

    }
}