package com.example.mohammed.movieappmvpkotlin.model

interface OnDataListener {
    fun onSuccess(movieList: List<Movie>)
    fun onFailure(msg: String)
}
