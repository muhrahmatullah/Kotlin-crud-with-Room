package com.rahmat.app.samplecrudkotlin.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rahmat.app.samplecrudkotlin.DetailActivity
import com.rahmat.app.samplecrudkotlin.R
import com.rahmat.app.samplecrudkotlin.entity.Student
import kotlinx.android.synthetic.main.student_item_view.view.*


/**
 * Created by muhrahmatullah on 12/08/18.
 */
class ItemAdapter(val studentList : ArrayList<Student>, val context : Context)
    : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(context).inflate(
                R.layout.student_item_view, parent, false))
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindView(studentList[position])
        holder.cardView.setOnClickListener{ v ->
            val intent = Intent(v.context, DetailActivity::class.java)
            intent.putExtra("student_object", studentList[position])
            v.context.startActivity(intent)
        }
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val studentNameTV = itemView.item_name
        val studentNimTv = itemView.item_nim
        val studentGenTv = itemView.item_gender
        val cardView = itemView.item_container

        fun bindView(students : Student){
            studentNameTV.text = students.name
            studentGenTv.text = students.gender
            studentNimTv.text = students.nim
        }
    }
}