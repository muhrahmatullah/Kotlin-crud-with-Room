package com.rahmat.app.samplecrudkotlin.features.main

import android.text.method.TextKeyListener.clear
import com.rahmat.app.samplecrudkotlin.adapter.ItemAdapter
import com.rahmat.app.samplecrudkotlin.db.StudentDao
import com.rahmat.app.samplecrudkotlin.entity.Student
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by muhrahmatullah on 12/08/18.
 */
class MainPresenter @Inject constructor(val studentDao:StudentDao, mainView: MainContract.View): MainContract.Presenter{



    val compositeDisposable = CompositeDisposable()
    var student = ArrayList<Student>()

    var view:MainContract.View? = null

    init {
        this.view = mainView
    }

    override fun getAllStudent() {
        compositeDisposable.add(studentDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    student.clear()
                    student.addAll(it)
                })

        view?.showStudent(student)
    }

    override fun insertNewStudent(studentName: String, studentNim: String, studentGender: String) {
        val newStudent= Student(name = studentName, nim = studentNim, gender = studentGender )
    }
}