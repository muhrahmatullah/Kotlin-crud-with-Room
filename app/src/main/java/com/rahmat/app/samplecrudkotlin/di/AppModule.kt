package com.rahmat.app.samplecrudkotlin.di

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.rahmat.app.samplecrudkotlin.db.StudentDao
import com.rahmat.app.samplecrudkotlin.db.StudentDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by muhrahmatullah on 27/08/18.
 */
@Suppress("unused")
@Module
class AppModule{

    @Provides
    @Singleton
    internal fun provideContext(application: Application) : Context = application


    @Provides
    @DbName
    fun provideDbName() = "studentdata.db"


    @Provides @Singleton fun provideStudentDatabase(context: Context, @DbName dbName: String): StudentDatabase{
        return Room.databaseBuilder(context,
                StudentDatabase::class.java, dbName)
                .build()
    }

    @Provides @Singleton fun provideStudentDao(studentDatabase: StudentDatabase) = studentDatabase.studentDao()
}