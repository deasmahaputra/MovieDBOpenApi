package com.test.axiata.apps.movie

interface MovieContract{

    fun startLoading()

    fun stopLoading()

    fun onError(message : String)

    fun showServiceUnavailablePage()
}