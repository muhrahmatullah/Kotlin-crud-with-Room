package com.rahmat.app.samplecrudkotlin.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.rahmat.app.samplecrudkotlin.entity.Student

/**
 * Created by muhrahmatullah on 12/08/18.
 */
@Database(entities = [Student::class], version = 1)
abstract class StudentDatabase : RoomDatabase() {

    abstract fun studentDao(): StudentDao
    companion object {
        private var INSTANCE: StudentDatabase? = null

        fun getInstance(context: Context): StudentDatabase? {
            if (INSTANCE == null) {
                synchronized(StudentDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            StudentDatabase::class.java, "studentdata.db")
                            .build()
                }
            }
            return INSTANCE as StudentDatabase
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}