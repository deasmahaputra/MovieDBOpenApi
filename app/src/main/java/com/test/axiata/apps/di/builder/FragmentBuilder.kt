package com.test.axiata.apps.di.builder

import com.test.axiata.apps.di.module.MovieDetailModule
import com.test.axiata.apps.di.module.MovieListModule
import com.test.axiata.apps.movie.details.DetailsMovieFragment
import com.test.axiata.apps.movie.details.DetailsMovieViewModel
import com.test.axiata.apps.movie.list.ListMovieFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector(modules = [(MovieListModule::class)])
    internal abstract fun bindMovieListFragment(): ListMovieFragment

    @ContributesAndroidInjector(modules = [(MovieDetailModule::class)])
    internal abstract fun bindMovieDetailFragment(): DetailsMovieFragment
}