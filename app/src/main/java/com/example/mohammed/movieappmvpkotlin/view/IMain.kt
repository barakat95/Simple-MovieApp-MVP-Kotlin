package com.example.mohammed.movieappmvpkotlin.view

import com.example.mohammed.movieappmvpkotlin.model.Movie

interface IMain {
    fun showProgress()
    fun hideProgress()
    fun updateView(moviesList: List<Movie>)
    fun viewError(msg: String)
}