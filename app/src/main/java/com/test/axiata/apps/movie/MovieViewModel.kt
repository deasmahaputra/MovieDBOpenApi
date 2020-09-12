package com.test.axiata.apps.movie

import com.test.axiata.apps.base.BaseViewModel
import com.test.axiata.apps.common.SingleLiveEvent
import com.test.axiata.apps.network.ApiResponseCallback
import com.test.axiata.apps.network.ApiService
import com.test.axiata.apps.network.NetworkError
import com.test.axiata.apps.network.response.MovieListResponse
import javax.inject.Inject

class MovieViewModel @Inject constructor(private var apiService : ApiService) : BaseViewModel<MovieContract>(apiService){

    val movieListResponse = SingleLiveEvent<MutableList<MovieListResponse.MovieList>>()

    fun fetchMovieList(page : Int){
        val disposable = apiService.fetchMovieList(page, object : ApiResponseCallback<MovieListResponse>{
            override fun onSuccess(response: MovieListResponse) {
                movieListResponse.value = response.results
            }

            override fun onError(error: NetworkError) {

            }

        })
        compositeDisposable.add(disposable)
    }

}