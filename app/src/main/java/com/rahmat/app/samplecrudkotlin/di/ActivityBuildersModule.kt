package com.rahmat.app.samplecrudkotlin.di

import androidx.lifecycle.ViewModel
import com.rahmat.app.samplecrudkotlin.features.detail.DetailActivity
import com.rahmat.app.samplecrudkotlin.features.detail.DetailViewModel
import com.rahmat.app.samplecrudkotlin.features.main.MainActivity
import com.rahmat.app.samplecrudkotlin.features.main.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by muhrahmatullah on 26/09/18.
 */
@Suppress("unused")
@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity() : MainActivity

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindingMainViewModel(mainActivityViewModel: MainActivityViewModel) : ViewModel

    @ContributesAndroidInjector
    abstract fun bindDetailActivity() : DetailActivity

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindingDetailViewModel(detailViewModel: DetailViewModel) : ViewModel
}