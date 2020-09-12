package com.test.axiata.apps.network

import com.test.axiata.apps.BuildConfig
import com.test.axiata.apps.extension.configured
import com.test.axiata.apps.extension.subscribe
import com.test.axiata.apps.network.response.MovieListResponse
import io.reactivex.disposables.Disposable

class ApiService(private val networkService: NetworkService){

    fun fetchMovieList(page : Int, callback: ApiResponseCallback<MovieListResponse>) : Disposable{
        return networkService.fetchMovieList(BuildConfig.APIKEY, page)
            .configured()
            .subscribe({callback.onSuccess(it)}, { error : NetworkError -> callback.onError(error)})
    }
}