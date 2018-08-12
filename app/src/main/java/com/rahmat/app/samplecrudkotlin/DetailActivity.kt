package com.rahmat.app.samplecrudkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.delete -> {
                Toast.makeText(this, "Delete", Toast.LENGTH_LONG).show()}
            R.id.edit -> {Toast.makeText(this, "Edit", Toast.LENGTH_LONG).show()}
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return true
    }
}
