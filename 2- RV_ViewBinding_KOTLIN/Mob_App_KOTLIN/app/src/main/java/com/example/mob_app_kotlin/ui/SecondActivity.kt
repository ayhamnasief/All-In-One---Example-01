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
import com.example.mob_app_kotlin.databinding.ActivitySecondBinding
import com.example.mob_app_kotlin.model.Chat

class SecondActivity : AppCompatActivity(), MyHandler{

    lateinit var binding:ActivitySecondBinding
    lateinit var myAdapter:MyAdapter
    var chats:ArrayList<Chat> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent.extras!!.getString("usr").toString() != null){
            var usr:String = intent.extras!!.getString("usr").toString()

            myAdapter = MyAdapter()
            myAdapter.myHandler = this

            binding.rv.adapter = myAdapter
            binding.rv.layoutManager = LinearLayoutManager(this)
            binding.rv.setHasFixedSize(true)

            binding.btnSend.setOnClickListener {
                if(!binding.etMessage.text.toString().isNullOrEmpty()){
                    chats.add(Chat(usr, binding.etMessage.text.toString(), R.drawable.ic_sms))
                    myAdapter.setChat(chats)
                    binding.etMessage.setText("")
                }
            }
        }
    }

    override fun handlerMethod(chat: Chat) {
        Toast.makeText(this, "SMS: " + chat.message, Toast.LENGTH_LONG).show()
    }
}