package com.rahmat.app.samplecrudkotlin.di

import com.rahmat.app.samplecrudkotlin.StudentApplication

/**
 * Created by muhrahmatullah on 26/09/18.
 */
class AppInjector {
    companion object {
        fun init(app: StudentApplication){
            DaggerAppComponent
                    .builder()
                    .application(app)
                    .build()
                    .inject(app)
        }
    }
}