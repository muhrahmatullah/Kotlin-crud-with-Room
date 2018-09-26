package com.rahmat.app.samplecrudkotlin.features.main

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.rahmat.app.samplecrudkotlin.R
import com.rahmat.app.samplecrudkotlin.R.id.fab_add
import com.rahmat.app.samplecrudkotlin.R.id.item_recyclerview
import com.rahmat.app.samplecrudkotlin.adapter.ItemAdapter
import com.rahmat.app.samplecrudkotlin.db.StudentDatabase
import com.rahmat.app.samplecrudkotlin.entity.Student
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.input_dialog.view.*

class MainActivity : AppCompatActivity() {

    private val students : ArrayList<Student> = ArrayList()

    private var studentDatabase:StudentDatabase? = null
    val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        item_recyclerview.layoutManager = LinearLayoutManager(applicationContext)
        item_recyclerview.adapter = ItemAdapter(students, this)
        studentDatabase = StudentDatabase.getInstance(this)
        getAllData()

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
                insertToDb(Student(studentName.toString(), studentNim.toString(), gender))
                applicationContext.toast("Data berhasil dimasukkan")

            }
            dialogBuilder.setNegativeButton("Batal") { _: DialogInterface, _: Int ->
            }
            dialogBuilder.show()
        }
    }

    fun insertToDb(student:Student){
        compositeDisposable.add(Observable.fromCallable{studentDatabase?.studentDao()?.insert(student)}
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }

    fun getAllData(){
        compositeDisposable.add(studentDatabase!!.studentDao().getAll()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    students.clear()
                    students.addAll(it)
                    item_recyclerview.adapter = ItemAdapter(students, this)
                })
    }
    override fun onDestroy() {
        super.onDestroy()
        StudentDatabase.destroyInstance()
        compositeDisposable.dispose()
    }

    fun Context.toast(message:CharSequence){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
