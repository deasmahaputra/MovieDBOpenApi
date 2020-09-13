package com.test.axiata.apps.di.builder

import com.test.axiata.apps.di.module.MovieModule
import com.test.axiata.apps.movie.MovieActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(MovieModule::class)])
    internal abstract fun bindMovieActivity(): MovieActivity

}