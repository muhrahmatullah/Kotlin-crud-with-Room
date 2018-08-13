package com.rahmat.app.samplecrudkotlin.features.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.rahmat.app.samplecrudkotlin.R
import com.rahmat.app.samplecrudkotlin.db.StudentDatabase
import com.rahmat.app.samplecrudkotlin.entity.Student
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*

class DetailActivity : AppCompatActivity() {

    lateinit var student:Student

    var studentDatabase:StudentDatabase? = null
    val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        student = intent.getParcelableExtra("student_object")

        textViewName.text = "Nama = ${student.name}"
        textViewNim.text = "Nim = ${student.nim}"
        textViewGender.text = "Jenis Kelamin = ${student.gender}"

        studentDatabase = StudentDatabase.getInstance(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.delete -> {
                deleteStudent(student)
                Toast.makeText(this, "Student Deleted name = ${student.name}", Toast.LENGTH_LONG).show()
                finish()
            }

            R.id.edit -> {Toast.makeText(this, "Edit", Toast.LENGTH_LONG).show()}
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return true
    }

    fun deleteStudent(student:Student){
        compositeDisposable.add(Observable.fromCallable{studentDatabase?.studentDao()?.delete(student)}
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }

    override fun onDestroy() {
        super.onDestroy()
        StudentDatabase.destroyInstance()
        compositeDisposable.dispose()
    }
}
