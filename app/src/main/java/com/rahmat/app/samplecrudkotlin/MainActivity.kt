package com.rahmat.app.samplecrudkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.rahmat.app.samplecrudkotlin.adapter.ItemAdapter
import com.rahmat.app.samplecrudkotlin.entity.Student
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val students : ArrayList<Student> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addDummyData()

        item_recyclerview.layoutManager = LinearLayoutManager(this)
        item_recyclerview.adapter = ItemAdapter(students, this)

    }

    fun addDummyData(){
        students.add(Student("Muhammad Rahmatullah", "D42114009", "Laki-laki"))
        students.add(Student("Muhammad Rahmatullah", "D42114009", "Laki-laki"))
        students.add(Student("Muhammad Rahmatullah", "D42114009", "Laki-laki"))
        students.add(Student("Muhammad Rahmatullah", "D42114009", "Laki-laki"))
    }
}
