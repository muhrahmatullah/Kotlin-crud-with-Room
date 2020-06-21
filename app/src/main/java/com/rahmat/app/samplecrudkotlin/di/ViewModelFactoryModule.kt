package com.rahmat.app.samplecrudkotlin.di

import androidx.lifecycle.ViewModelProvider
import com.rahmat.app.samplecrudkotlin.vmfactory.StudentViewModelFactory

/**
 * Created by muhrahmatullah on 27/09/18.
 */
abstract class ViewModelFactoryModule {


    internal abstract fun bindViewModelFactory(vMFactory : StudentViewModelFactory) : ViewModelProvider.Factory
}