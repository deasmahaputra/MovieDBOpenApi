package com.test.axiata.apps.movie

import com.test.axiata.apps.base.BaseViewModel
import javax.inject.Inject

class MovieViewModel @Inject constructor(private var apiService : ApiService) : BaseViewModel<GithubUserContract>(apiService){

}