package com.example.mohammed.movieappmvpkotlin.presenter

import com.example.mohammed.movieappmvpkotlin.model.Movie
import com.example.mohammed.movieappmvpkotlin.model.OnDataListener
import com.example.mohammed.movieappmvpkotlin.view.MainActivity

class Presenter : IPresenter {

    var view: MainActivity? = null
    var model: Movie? = null

    constructor(view: MainActivity?) {
        this.view = view
        model = Movie("", "", "", "")
        view!!.showProgress()
    }


    override fun getDataFromModel() {
        model!!.getMovies(object : OnDataListener {
            override fun onSuccess(movieList: List<Movie>) {
                view!!.hideProgress()
                view!!.updateView(movieList)

            }

            override fun onFailure(msg: String) {
                view!!.hideProgress()
                view!!.viewError(msg)
            }

        })
    }
}