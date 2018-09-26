package com.rahmat.app.samplecrudkotlin.di

import android.app.Application
import com.rahmat.app.samplecrudkotlin.StudentApplication
import com.rahmat.app.samplecrudkotlin.features.main.MainActivity
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by muhrahmatullah on 27/08/18.
 */
@Singleton
@Component(modules = [(AppModule::class), (AndroidSupportInjectionModule::class),
    (ActivityBuildersModule::class)])
interface AppComponent {

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(application: Application) : Builder

        fun build() : AppComponent
    }

    fun inject(app: StudentApplication)
}