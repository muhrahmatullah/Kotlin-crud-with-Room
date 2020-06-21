package com.rahmat.app.samplecrudkotlin.di

import android.app.Application
import androidx.room.Room
import android.content.Context
import com.rahmat.app.samplecrudkotlin.db.StudentDao
import com.rahmat.app.samplecrudkotlin.db.StudentDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

/**
 * Created by muhrahmatullah on 27/08/18.
 */
@InstallIn(ApplicationComponent::class)
@Module
class AppModule{

//    lateinit var context: Context
//
//    constructor(_context: Context) {
//        context = _context
//    }
//
//    @Provides
//    internal fun provideAppContext() : Context = context
//
//    @Provides
//    internal fun provideApplication() : Application = context.applicationContext as Application

    @Provides
    @DbName
    fun provideDbName() = "studentdata.db"


    @Provides fun provideStudentDatabase(@ApplicationContext context: Context, @DbName dbName: String): StudentDatabase{
        return Room.databaseBuilder(context,
                StudentDatabase::class.java, dbName)
                .build()
    }

    @Provides fun provideStudentDao(studentDatabase: StudentDatabase) = studentDatabase.studentDao()

    @Provides fun provideCompositeDisposable() = CompositeDisposable()
}