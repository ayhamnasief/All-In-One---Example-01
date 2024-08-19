package com.example.mob_app_kotlin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.mob_app_kotlin.R

class MainActivity : AppCompatActivity() {

    lateinit var et_usr:EditText
    lateinit var et_pass:EditText
    lateinit var btn_login:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        et_usr = findViewById(R.id.et_usr)
        et_pass = findViewById(R.id.et_pass)
        btn_login = findViewById(R.id.btn_login)

        btn_login.setOnClickListener {
            if(!et_usr.text.toString().isNullOrEmpty() && !et_pass.text.toString().isNullOrEmpty()){
                var intent:Intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("usr", et_usr.text.toString())
                startActivity(intent)
            }
        }
    }
}