package com.example.mob_app_kotlin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import com.example.mob_app_kotlin.R
import com.example.mob_app_kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            if(!binding.etUsr.text.toString().isNullOrEmpty() && !binding.etPass.text.toString().isNullOrEmpty()){
                var intent:Intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("usr", binding.etUsr.text.toString())
                startActivity(intent)
            }
        }
    }
}