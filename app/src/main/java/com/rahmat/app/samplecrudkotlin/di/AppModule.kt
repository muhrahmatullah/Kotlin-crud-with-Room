package com.rahmat.app.samplecrudkotlin.di

import android.arch.persistence.room.Room
import android.content.Context
import com.rahmat.app.samplecrudkotlin.db.StudentDatabase
import dagger.Module
import dagger.Provides

/**
 * Created by muhrahmatullah on 12/08/18.
 */
@Module class AppModule(private val context:Context){

    @Provides fun provideAppContext() = context

    @Provides fun provideAppDatabase(context: Context): StudentDatabase =
            Room.databaseBuilder(context, StudentDatabase::class.java, "studentdata.db")
                    .build()

    @Provides fun provideStudentDao(studentDatabase: StudentDatabase) = studentDatabase.studentDao()

}