package com.test.axiata.apps.network

interface ApiResponseCallback<T> {
    fun onSuccess(response: T)
    fun onError(error: NetworkError)
}