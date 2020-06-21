package com.rahmat.app.samplecrudkotlin.features.detail

import android.annotation.SuppressLint
import androidx.lifecycle.Observer
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.rahmat.app.samplecrudkotlin.R
import com.rahmat.app.samplecrudkotlin.databinding.ActivityDetailBinding
import com.rahmat.app.samplecrudkotlin.db.StudentDatabase
import com.rahmat.app.samplecrudkotlin.entity.Student
import com.rahmat.app.samplecrudkotlin.features.base.BaseActivity

import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*
import kotlinx.android.synthetic.main.input_dialog.view.*

class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>() {
    override fun getViewModelBindingVariable(): Int {
        return NO_VIEW_MODEL_BINDING_VARIABLE
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_detail
    }

    private lateinit var studentId:String
    lateinit var student: Student

    var studentDatabase:StudentDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        studentId = intent.getLongExtra("student_id", 0).toString()

        getViewModel().getStudentbyId().observe(this, Observer {
            student = it!!
            displayStudent(it)
        })

        getViewModel().triggerFetchData(studentId)

        studentDatabase = StudentDatabase.getInstance(this)
    }

    fun displayStudent(student: Student?){
        textViewName.text = "Nama = ${student?.name}"
        textViewNim.text = "Nim = ${student?.nim}"
        textViewGender.text = "Jenis Kelamin = ${student?.gender}"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    @SuppressLint("InflateParams")
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.delete -> {
                getViewModel().deleteData(student)
                Toast.makeText(this, "Data  ${student.name} berhasil dihapus", Toast.LENGTH_LONG).show()
                finish()
            }
            R.id.edit -> {
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
                    val gender: String
                    val selectedRadioButton = radioGroupGender.checkedRadioButtonId
                    gender = when (selectedRadioButton) {
                        R.id.radio_female -> "Perempuan"
                        else -> "Laki-laki"
                    }
                    getViewModel().updateData(student.id, studentName.toString(), studentNim.toString(), gender)
                    Toast.makeText(this, "Data berhasil diubah $studentName ", Toast.LENGTH_LONG).show()
                }
                dialogBuilder.setNegativeButton("Batal") { _: DialogInterface, _: Int ->
                }
                dialogBuilder.show()
            }
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return true
    }
}
