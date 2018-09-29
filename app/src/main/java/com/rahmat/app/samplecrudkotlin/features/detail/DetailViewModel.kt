package com.rahmat.app.samplecrudkotlin.features.detail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import com.rahmat.app.samplecrudkotlin.db.repository.LocalRepository
import com.rahmat.app.samplecrudkotlin.entity.Student
import com.rahmat.app.samplecrudkotlin.features.base.BaseViewModel
import javax.inject.Inject

/**
 * Created by muhrahmatullah on 29/09/18.
 */
class DetailViewModel @Inject constructor(private val localRepository: LocalRepository): BaseViewModel() {

    val mTriger : MutableLiveData<String> = MutableLiveData()
    val student : LiveData<Student> = Transformations.switchMap(mTriger) {
        localRepository.getDataById(it)
    }

    fun getStudentbyId() = student

    fun triggerFetchData(id: String){
        mTriger.value = id
    }

    fun deleteData(student: Student){
        localRepository.deleteData(student)
    }

    fun updateData(id: Long, name: String, nim: String, gen: String){
        localRepository.updateData(id, name, nim, gen)
    }

}