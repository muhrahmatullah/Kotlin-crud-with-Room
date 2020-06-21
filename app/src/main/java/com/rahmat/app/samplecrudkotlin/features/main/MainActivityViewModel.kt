package com.rahmat.app.samplecrudkotlin.features.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.rahmat.app.samplecrudkotlin.R.id.item_recyclerview
import com.rahmat.app.samplecrudkotlin.adapter.ItemAdapter
import com.rahmat.app.samplecrudkotlin.db.StudentDao
import com.rahmat.app.samplecrudkotlin.db.repository.LocalRepository
import com.rahmat.app.samplecrudkotlin.entity.Student
import com.rahmat.app.samplecrudkotlin.features.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject



/**
 * Created by muhrahmatullah on 26/09/18.
 */
class MainActivityViewModel @Inject constructor(private val localRepository: LocalRepository) : BaseViewModel() {

    private var mTriggerFetchData = MutableLiveData<Boolean>()
    private var student : LiveData<List<Student>> = Transformations.switchMap(mTriggerFetchData){
        localRepository.getAllData()
    }

    fun insertStudent(student: Student){
        localRepository.insertData(student)
    }

    fun getStudents(): LiveData<List<Student>>{
        return student
    }

    fun loadStudent() {
        mTriggerFetchData.value = true
    }
}