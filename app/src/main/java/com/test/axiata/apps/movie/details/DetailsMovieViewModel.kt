package com.test.axiata.apps.movie.details

import com.test.axiata.apps.base.BaseViewModel
import com.test.axiata.apps.common.SingleLiveEvent
import com.test.axiata.apps.movie.MovieContract
import com.test.axiata.apps.network.ApiResponseCallback
import com.test.axiata.apps.network.ApiService
import com.test.axiata.apps.network.NetworkError
import com.test.axiata.apps.network.response.DetailsMovieResponse
import com.test.axiata.apps.network.response.TrailerResponse
import javax.inject.Inject

class DetailsMovieViewModel @Inject constructor(private val apiService : ApiService) : BaseViewModel<DetailsMovieContract>(apiService){

    val errorMessages = SingleLiveEvent<String>()
    val detailsMovieData = SingleLiveEvent<DetailsMovieResponse>()
    val trailerMovieData = SingleLiveEvent<TrailerResponse>()

    fun fetchMovieDetails(movieId : Int){
        val disposable = apiService.fetchMovieDetails(movieId, object : ApiResponseCallback<DetailsMovieResponse>{
            override fun onSuccess(response: DetailsMovieResponse) {
                detailsMovieData.value = response
            }

            override fun onError(error: NetworkError) {
                errorMessages.value = error.getErrorMessage()
            }

        })
        compositeDisposable.add(disposable)
    }

    fun fetchTrailerVideo(movieId: Int){
        val disposable = apiService.fetchTrailerVideo(movieId, object : ApiResponseCallback<TrailerResponse>{
            override fun onSuccess(response: TrailerResponse) {
                trailerMovieData.value = response
            }

            override fun onError(error: NetworkError) {
                errorMessages.value = error.getErrorMessage()
            }
        })
        compositeDisposable.add(disposable)
    }
}