package com.rahmat.app.samplecrudkotlin.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by muhrahmatullah on 12/08/18.
 */
@Parcelize
@Entity(tableName = "students")
data class Student(@ColumnInfo(name = "name")var name: String,
                   @ColumnInfo(name = "nim")var nim: String,
                   @ColumnInfo(name = "gender")var gender: String,
                   @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long = 0): Parcelable