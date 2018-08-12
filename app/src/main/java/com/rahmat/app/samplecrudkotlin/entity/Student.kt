package com.rahmat.app.samplecrudkotlin.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by muhrahmatullah on 12/08/18.
 */
@Parcelize
data class Student(var name: String, var nim: String, var gender: String): Parcelable