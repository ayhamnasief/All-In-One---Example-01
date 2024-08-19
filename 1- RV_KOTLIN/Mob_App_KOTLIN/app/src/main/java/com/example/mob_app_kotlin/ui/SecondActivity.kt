package com.example.mob_app_kotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mob_app_kotlin.R
import com.example.mob_app_kotlin.adapter.MyAdapter
import com.example.mob_app_kotlin.adapter.MyHandler
import com.example.mob_app_kotlin.model.Chat

class SecondActivity : AppCompatActivity(), MyHandler{

    lateinit var rv:RecyclerView
    lateinit var et_message:EditText
    lateinit var btn_send:Button
    lateinit var myAdapter:MyAdapter
    var chats:ArrayList<Chat> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        if(intent.extras!!.getString("usr").toString() != null){
            var usr:String = intent.extras!!.getString("usr").toString()

            myAdapter = MyAdapter()
            myAdapter.myHandler = this

            rv = findViewById(R.id.rv)
            rv.adapter = myAdapter
            rv.layoutManager = LinearLayoutManager(this)
            rv.setHasFixedSize(true)

            btn_send = findViewById(R.id.btn_send)
            et_message = findViewById(R.id.et_message)

            btn_send.setOnClickListener {
                if(!et_message.text.toString().isNullOrEmpty()){
                    chats.add(Chat(usr, et_message.text.toString(), R.drawable.ic_sms))
                    myAdapter.setChat(chats)
                    et_message.setText("")
                }
            }
        }
    }

    override fun handlerMethod(chat: Chat) {
        Toast.makeText(this, "SMS: " + chat.message, Toast.LENGTH_LONG).show()
    }
}