package com.test.axiata.apps.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(::class)])
    internal abstract fun bindGithubUser(): GithubUserActivity

}