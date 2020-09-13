package com.test.axiata.apps.movie

import com.test.axiata.apps.base.BaseViewModel
import com.test.axiata.apps.common.SingleLiveEvent
import com.test.axiata.apps.network.ApiResponseCallback
import com.test.axiata.apps.network.ApiService
import com.test.axiata.apps.network.NetworkError
import com.test.axiata.apps.network.response.GenreResponse
import com.test.axiata.apps.network.response.MovieListResponse
import javax.inject.Inject

class MovieViewModel @Inject constructor(private var apiService : ApiService) : BaseViewModel<MovieContract>(apiService){

    val movieListResponse = SingleLiveEvent<MutableList<MovieListResponse.MovieList>>()
    val movieGenreResponse = SingleLiveEvent<GenreResponse>()
    val shimmerVisibility = SingleLiveEvent<Boolean>()
    val errorMessages = SingleLiveEvent<String>()
    val serviceUnavailable = SingleLiveEvent<Boolean>()

    fun fetchMovieList(page : Int, genreId : Int){
        shimmerVisibility.value = true
        val disposable = apiService.fetchMovieList(page, genreId, object : ApiResponseCallback<MovieListResponse>{
            override fun onSuccess(response: MovieListResponse) {
                movieListResponse.value = response.results
                shimmerVisibility.value = false
            }

            override fun onError(error: NetworkError) {
                shimmerVisibility.value = false
                errorMessages.value = error.getErrorMessage()
                if(error.isNetworkError()) serviceUnavailable.value = true
            }

        })
        compositeDisposable.add(disposable)
    }

    fun fetchMovieGenre(){
        val disposable = apiService.fetchMovieGenre(object : ApiResponseCallback<GenreResponse>{
            override fun onSuccess(response: GenreResponse) {
                movieGenreResponse.value = response
            }

            override fun onError(error: NetworkError) {
                errorMessages.value = error.getErrorMessage()
            }

        })
        compositeDisposable.add(disposable)
    }

}