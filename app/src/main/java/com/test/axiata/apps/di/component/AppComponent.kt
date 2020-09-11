package com.test.axiata.apps.di.component

import android.app.Application
import com.test.axiata.apps.base.MovieApplication
import com.test.axiata.apps.di.builder.ActivityBuilder
import com.test.axiata.apps.di.builder.FragmentBuilder
import com.test.axiata.apps.di.module.AppModule
import com.test.axiata.apps.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidInjectionModule::class), (AppModule::class), (NetworkModule::class),
    (ActivityBuilder::class),(FragmentBuilder::class)])

interface AppComponent {

    fun inject(app: MovieApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}