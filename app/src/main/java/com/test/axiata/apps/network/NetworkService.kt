package com.test.axiata.apps.network

import com.test.axiata.apps.BuildConfig
import com.test.axiata.apps.network.response.MovieListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService{

    @GET(BuildConfig.ENDPOINT + "3/movie/popular")
    fun fetchMovieList(@Query("api_key") apiKey : String,
                       @Query("page") page : Int) : Observable<MovieListResponse>


}