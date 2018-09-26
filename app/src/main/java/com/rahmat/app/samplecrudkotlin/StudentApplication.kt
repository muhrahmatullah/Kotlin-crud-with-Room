package com.rahmat.app.samplecrudkotlin

import android.app.Activity
import android.app.Application
import com.rahmat.app.samplecrudkotlin.db.StudentDatabase
import com.rahmat.app.samplecrudkotlin.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by muhrahmatullah on 27/08/18.
 */
class StudentApplication : Application(), HasActivityInjector{

    @Inject
    lateinit var mActivityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return mActivityInjector
    }

    companion object {
        var studentDatabase: StudentDatabase? = null
    }
    override fun onCreate() {
        super.onCreate()

        AppInjector.init(this)

        studentDatabase = StudentDatabase.getInstance(this)
    }


}