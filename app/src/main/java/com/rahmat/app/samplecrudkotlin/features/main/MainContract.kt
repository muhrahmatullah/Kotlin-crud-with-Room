package com.rahmat.app.samplecrudkotlin.features.main

import com.rahmat.app.samplecrudkotlin.entity.Student

/**
 * Created by muhrahmatullah on 12/08/18.
 */
interface MainContract {
    interface View{
        fun showStudent(task:List<Student>)

    }
    interface Presenter{
        fun getAllStudent()
        fun insertNewStudent(studentName: String, studentNim: String, studentGender: String)

    }
}