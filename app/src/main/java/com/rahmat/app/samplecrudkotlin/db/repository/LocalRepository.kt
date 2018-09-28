package com.rahmat.app.samplecrudkotlin.db.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import com.rahmat.app.samplecrudkotlin.db.StudentDao
import com.rahmat.app.samplecrudkotlin.entity.Student
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by muhrahmatullah on 29/09/18.
 */
@Singleton
class LocalRepository @Inject constructor(studentDao: StudentDao, compositeDisposable: CompositeDisposable) {

    var stDao : StudentDao = studentDao
    var comp : CompositeDisposable = compositeDisposable

    fun getAllData() : LiveData<List<Student>>{
        return LiveDataReactiveStreams.fromPublisher(stDao.getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation()))
    }

    fun insertData(student: Student){
        comp.add(Observable.fromCallable {stDao.insert(student)}
                .subscribeOn(Schedulers.computation())
                .subscribe())
    }

}