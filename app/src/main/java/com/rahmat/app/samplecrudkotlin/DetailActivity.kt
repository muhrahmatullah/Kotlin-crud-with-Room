package com.rahmat.app.samplecrudkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rahmat.app.samplecrudkotlin.entity.Student

import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*

class DetailActivity : AppCompatActivity() {

    lateinit var student:Student

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        student = intent.getParcelableExtra("student_object")

        textViewName.text = "Nama = ${student.name}"
        textViewNim.text = "Nim = ${student.nim}"
        textViewGender.text = "Jenis Kelamin = ${student.gender}"
    }

}
