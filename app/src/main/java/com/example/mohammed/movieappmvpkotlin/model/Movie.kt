package com.example.mohammed.movieappmvpkotlin.model

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

data class Movie(var title: String, var release_date: String, var overview: String, var poster_path: String) : IModel {
    override fun getMovies(listener: OnDataListener) {

        val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://api.themoviedb.org/3/")
                .build()

        val client = retrofit.create(ApiInterface::class.java)
        val responseCall = client.getTopRatedMovies("cfb831865e3176bd0a48f961f155bfb5")

        responseCall.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(call: Call<MoviesResponse>?, response: Response<MoviesResponse>?) {
                listener.onSuccess(response!!.body().results)
            }

            override fun onFailure(call: Call<MoviesResponse>?, t: Throwable?) {
                listener.onFailure(t!!.message.toString())
            }
        })
    }
}