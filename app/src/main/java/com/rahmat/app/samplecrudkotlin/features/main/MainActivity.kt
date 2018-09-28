package com.rahmat.app.samplecrudkotlin.features.main

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.rahmat.app.samplecrudkotlin.R
import com.rahmat.app.samplecrudkotlin.adapter.ItemAdapter
import com.rahmat.app.samplecrudkotlin.databinding.ActivityMainBinding
import com.rahmat.app.samplecrudkotlin.db.StudentDao
import com.rahmat.app.samplecrudkotlin.entity.Student
import com.rahmat.app.samplecrudkotlin.features.base.BaseActivity
import dagger.android.AndroidInjection
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.input_dialog.view.*
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>() {

    override fun getViewModelBindingVariable(): Int {
        return NO_VIEW_MODEL_BINDING_VARIABLE
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    private val students : ArrayList<Student> = ArrayList()

    lateinit var adapter:ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        item_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = ItemAdapter(students, this)
        item_recyclerview.adapter = adapter

        getViewModel().getStudents().observe(this, Observer<List<Student>> {
            students.clear()
            students.addAll(it!!)
            adapter.notifyDataSetChanged()
        })

        getViewModel().loadStudent()

        fab_add.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(this)
            val view = layoutInflater.inflate(R.layout.input_dialog, null)
            dialogBuilder.setView(view)
            dialogBuilder.setTitle("Masukkan data baru")
            val et_name = view.ed_student_name
            val et_nim = view.ed_student_id
            val radioGroupGender = view.radio_group_gender
            dialogBuilder.setPositiveButton("Tambahkan") { _: DialogInterface, _: Int ->
                val studentName = et_name.text
                val studentNim = et_nim.text
                var gender: String
                val selectedRadioButton = radioGroupGender.checkedRadioButtonId
                Log.v("test", "" + selectedRadioButton)
                when (selectedRadioButton) {
                    R.id.radio_female -> gender = "Perempuan"
                    else -> gender = "Laki-laki"
                }
                getViewModel().insertStudent(Student(studentName.toString(),
                        studentNim.toString(), gender))
                applicationContext.toast("Data berhasil dimasukkan")

            }
            dialogBuilder.setNegativeButton("Batal") { _: DialogInterface, _: Int ->
            }
            dialogBuilder.show()
        }
    }

    override fun onResume() {
        super.onResume()
        getViewModel().loadStudent()

    }

    fun Context.toast(message:CharSequence){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
