package com.rahmat.app.samplecrudkotlin.db

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.rahmat.app.samplecrudkotlin.entity.Student
import io.reactivex.Flowable

/**
 * Created by muhrahmatullah on 12/08/18.
 */
@Dao
interface StudentDao {
    @Query("SELECT * from students")
    fun getAll(): Flowable<List<Student>>

    @Insert(onConflict = REPLACE)
    fun insert(student: Student)

    @Delete
    fun delete(student: Student)

    @Update(onConflict = REPLACE)
    fun update(student: Student)
}