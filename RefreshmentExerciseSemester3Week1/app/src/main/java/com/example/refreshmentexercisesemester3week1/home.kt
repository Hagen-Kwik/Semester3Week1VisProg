package com.example.refreshmentexercisesemester3week1

import Interface.CardListener
import Model.animal
import adapter.RecyclerViewAdapter
import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_home.*
import kotlin.math.log

class home : AppCompatActivity(),CardListener {

    private val adapter = RecyclerViewAdapter(animalARRAY, this)

    companion object {
        var animalARRAY: ArrayList<animal> = ArrayList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()


        setupRecycler()
        listener()
    }

    override fun onResume() {
        super.onResume()

        adapter.notifyDataSetChanged()
    }

    fun listener(){
        newbutton.setOnClickListener{
            val myIntent = Intent(this, masukin_data_activity::class.java)
            startActivity(myIntent)
        }
    }

    fun setupRecycler(){

        recyclerviewhome.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerviewhome.adapter = adapter

    }

    override fun editCLICKED(position: Int) {
        val myIntent = Intent(this, masukin_data_activity::class.java).apply{
            putExtra("position",position)
        }
        startActivity(myIntent)
    }

    override fun deleteCLICKED(position: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Do You Want to Delete?")
        builder.setMessage("Once deleted it will never come back")

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            Toast.makeText(applicationContext,
                android.R.string.yes, Toast.LENGTH_SHORT).show()

            animalARRAY.removeAt(position)

            onResume()
        }

        builder.setNegativeButton(android.R.string.no) { dialog, which ->
            Toast.makeText(applicationContext,
                android.R.string.no, Toast.LENGTH_SHORT).show()
        }

        builder.show()


    }

}