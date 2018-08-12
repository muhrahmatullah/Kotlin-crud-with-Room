package com.rahmat.app.samplecrudkotlin.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.rahmat.app.samplecrudkotlin.entity.Student

/**
 * Created by muhrahmatullah on 12/08/18.
 */
@Database(entities = arrayOf(Student::class), version = 1)
abstract class StudentDatabase : RoomDatabase() {

    abstract fun studentDao(): StudentDao

    companion object {
        private var INSTANCE: StudentDatabase? = null

        fun getInstance(context: Context): StudentDatabase? {
            if (INSTANCE == null) {
                synchronized(StudentDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            StudentDatabase::class.java, "studentdata.db")
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null

        }

    }
}