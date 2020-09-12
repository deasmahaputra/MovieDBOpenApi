package com.test.axiata.apps.network.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieListResponse(var results : MutableList<MovieList>) : Parcelable{

    @Parcelize
    data class MovieList(var id: Int? = null,
                         var title: String? = null,
                         var poster_path: String? = null,
                         var description : String? = null) : Parcelable
}