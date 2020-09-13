package com.test.axiata.apps.network

import com.test.axiata.apps.BuildConfig
import com.test.axiata.apps.network.response.DetailsMovieResponse
import com.test.axiata.apps.network.response.GenreResponse
import com.test.axiata.apps.network.response.MovieListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService{

    @GET(BuildConfig.ENDPOINT + "3/movie/{genre_id}/lists")
    fun fetchMovieList(@Path("genre_id") genreId : Int,
                       @Query("api_key") apiKey : String,
                       @Query("page") page : Int) : Observable<MovieListResponse>


    @GET(BuildConfig.ENDPOINT + "3/genre/movie/list")
    fun fetchMovieGenre(@Query("api_key") apiKey : String) : Observable<GenreResponse>

    @GET(BuildConfig.ENDPOINT + "3/movie/{movie_id}")
    fun fetchDetailMovie(@Path("movie_id") movieId : Int,
                         @Query("api_key") apiKey : String) : Observable<DetailsMovieResponse>

}