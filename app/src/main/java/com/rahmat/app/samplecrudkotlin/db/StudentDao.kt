package com.rahmat.app.samplecrudkotlin.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.rahmat.app.samplecrudkotlin.entity.Student

/**
 * Created by muhrahmatullah on 12/08/18.
 */
@Dao
interface StudentDao {
    @Query("SELECT * from students")
    fun getAll(): List<Student>

    @Insert(onConflict = REPLACE)
    fun insert(student: Student)
}