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
import com.rahmat.app.samplecrudkotlin.adapter.ItemAdapter
import com.rahmat.app.samplecrudkotlin.entity.Student
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.input_dialog.view.*

class MainActivity : AppCompatActivity() {

    val students : ArrayList<Student> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        item_recyclerview.layoutManager = LinearLayoutManager(this)
        item_recyclerview.adapter = ItemAdapter(students, this)

        fab_add.setOnClickListener{
            val dialogBuilder = AlertDialog.Builder(this)
            val view = layoutInflater.inflate(R.layout.input_dialog, null)
            dialogBuilder.setView(view)
            dialogBuilder.setTitle("Masukkan data baru")
            val et_name = view.ed_student_name
            val et_nim = view.ed_student_id
            val radioGroupGender = view.radio_group_gender
            dialogBuilder.setPositiveButton("Tambahkan") { _ : DialogInterface, _: Int ->
                val studentName = et_name.text
                val studentNim = et_nim.text
                var gender:String
                val selectedRadioButton = radioGroupGender.checkedRadioButtonId
                Log.v("test", ""+selectedRadioButton)
                when(selectedRadioButton){
                    R.id.radio_female -> gender = "Perempuan"
                    else -> gender = "Laki-laki"
                }
                applicationContext.toast("$studentName $studentNim $gender")
            }
            dialogBuilder.setNegativeButton("Batal"){ _: DialogInterface, _: Int ->
            }
            dialogBuilder.show()
        }
    }

    fun Context.toast(message: CharSequence) =
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
