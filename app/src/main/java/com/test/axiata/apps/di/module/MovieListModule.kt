package com.test.axiata.apps.di.module

import androidx.lifecycle.ViewModelProvider
import com.test.axiata.apps.base.ViewModelProviderFactory
import com.test.axiata.apps.movie.MovieViewModel
import com.test.axiata.apps.network.ApiService
import dagger.Module
import dagger.Provides

@Module
class MovieListModule{

    @Provides
    internal fun provideHomeModule(apiService: ApiService): MovieViewModel {
        return MovieViewModel(apiService)
    }

    @Provides
    internal fun homeModelProvider(movieViewModel: MovieViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(movieViewModel)
    }
}