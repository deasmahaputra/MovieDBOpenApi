package com.test.axiata.apps.di.builder

import com.test.axiata.apps.di.module.MovieListModule
import com.test.axiata.apps.movie.list.ListMovieFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector(modules = [(MovieListModule::class)])
    internal abstract fun bindMovieListFragment(): ListMovieFragment
}