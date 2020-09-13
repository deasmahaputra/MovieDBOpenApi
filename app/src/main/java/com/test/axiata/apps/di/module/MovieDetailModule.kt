package com.test.axiata.apps.di.module

import androidx.lifecycle.ViewModelProvider
import com.test.axiata.apps.base.ViewModelProviderFactory
import com.test.axiata.apps.movie.MovieViewModel
import com.test.axiata.apps.movie.details.DetailsMovieViewModel
import com.test.axiata.apps.network.ApiService
import dagger.Module
import dagger.Provides

@Module
class MovieDetailModule{

    @Provides
    internal fun provideHomeModule(apiService: ApiService): DetailsMovieViewModel {
        return DetailsMovieViewModel(apiService)
    }

    @Provides
    internal fun homeModelProvider(detailsMovieViewModel: DetailsMovieViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(detailsMovieViewModel)
    }
}