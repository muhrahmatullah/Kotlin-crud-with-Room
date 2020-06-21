package com.rahmat.app.samplecrudkotlin.features.base

import androidx.lifecycle.ViewModel

/**
 * Created by muhrahmatullah on 26/09/18.
 */
abstract class BaseViewModel : ViewModel() {

    override fun onCleared() {
        super.onCleared()
    }

}