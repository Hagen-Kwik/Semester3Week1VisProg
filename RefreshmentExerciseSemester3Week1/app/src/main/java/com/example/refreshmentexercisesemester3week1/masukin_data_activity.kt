package com.example.refreshmentexercisesemester3week1

import Model.animal
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.core.net.toUri
import kotlinx.android.synthetic.main.activity_masukin_data.*
import java.io.File

class masukin_data_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_masukin_data)
        supportActionBar?.hide()
        uri = null
        listener()

    }

    val REQUEST_CODE = 100

    var uri: Uri? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE){
            PICTURE.setImageURI(data?.data) // handle chosen image
            uri = data?.data!!
            Log.d("here", uri.toString())
        }
    }
    fun listener(){


        pictbutoton.setOnClickListener{
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_CODE)
        }

        back.setOnClickListener{
            finish()
        }

        val dataforedit: Int = intent.getIntExtra("position", -1)

        if (dataforedit != -1) {
            nama.getEditText()?.setText(home.animalARRAY.get(dataforedit).nama)
            jenis.getEditText()?.setText(home.animalARRAY.get(dataforedit).jenis)
            usia.getEditText()?.setText(home.animalARRAY.get(dataforedit).usia.toString())
            if (home.animalARRAY.get(dataforedit).imageuri.toString().isNotEmpty()) {
                PICTURE.setImageURI(home.animalARRAY.get(dataforedit).imageuri)
            }

            tambahORedit.text = "Edit Hewan"
        }

        finishbutton.setOnClickListener{
            var name = nama.editText?.text.toString().trim()
            var type = jenis.editText?.text.toString().trim()
            var age = usia.editText?.text.toString().trim()

            var checking = true

            usia.error = ""
            nama.error = ""
            jenis.error = ""

            if (name!!.isEmpty()) {
                    nama.error = "Masukan Nama dengan Benar"
                    checking = false
            }

            if (type!!.isEmpty()) {
                    jenis.error = "Masukan Jenis dengan Benar"
                    checking = false
            }

            if (age!!.isEmpty()) {
                    usia.error = "Masukan Usia dengan Benar"
                    checking = false
            }

            if (checking) {

                Log.d("here",uri.toString())

                if (dataforedit != -1) {
                    if(uri != null) {
                        home.animalARRAY[dataforedit] = animal(name, type, age.toInt(), uri)
                    } else{
                        uri = home.animalARRAY[dataforedit].imageuri
                        home.animalARRAY[dataforedit] = animal(name, type, age.toInt(), uri)
                    }

                    finish()

                } else {
                    home.animalARRAY.add(animal(name, type, age.toInt(), uri))
                    finish()
                }
            }
        }
    }


}