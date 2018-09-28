package com.rahmat.app.samplecrudkotlin.di

import android.arch.lifecycle.ViewModelProvider
import com.rahmat.app.samplecrudkotlin.vmfactory.StudentViewModelFactory
import dagger.Binds
import dagger.Module

/**
 * Created by muhrahmatullah on 27/09/18.
 */
@Module
abstract class ViewModelFactoryModule {

    @Binds
    internal abstract fun bindViewModelFactory(vMFactory : StudentViewModelFactory) : ViewModelProvider.Factory
}