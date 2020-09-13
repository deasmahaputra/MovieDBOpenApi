package com.test.axiata.apps.network

import com.test.axiata.apps.BuildConfig
import com.test.axiata.apps.extension.configured
import com.test.axiata.apps.extension.subscribe
import com.test.axiata.apps.network.response.DetailsMovieResponse
import com.test.axiata.apps.network.response.GenreResponse
import com.test.axiata.apps.network.response.MovieListResponse
import io.reactivex.disposables.Disposable

class ApiService(private val networkService: NetworkService){

    fun fetchMovieList(page : Int, genreId : Int, callback: ApiResponseCallback<MovieListResponse>) : Disposable{
        return networkService.fetchMovieList(genreId, BuildConfig.APIKEY, page)
            .configured()
            .subscribe({callback.onSuccess(it)}, { error : NetworkError -> callback.onError(error)})
    }

    fun fetchMovieGenre(callback: ApiResponseCallback<GenreResponse>) : Disposable{
        return networkService.fetchMovieGenre(BuildConfig.APIKEY)
            .configured()
            .subscribe({callback.onSuccess(it)}, { error : NetworkError -> callback.onError(error)})
    }

    fun fetchMovieDetails(movieId : Int, callback: ApiResponseCallback<DetailsMovieResponse>) : Disposable{
        return networkService.fetchDetailMovie(movieId, BuildConfig.APIKEY)
            .configured()
            .subscribe({callback.onSuccess(it)}, { error : NetworkError -> callback.onError(error)})
    }
}