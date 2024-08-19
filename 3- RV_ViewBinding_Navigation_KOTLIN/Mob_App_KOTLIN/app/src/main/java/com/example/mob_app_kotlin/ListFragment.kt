package com.example.mob_app_kotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mob_app_kotlin.adapter.MyAdapter
import com.example.mob_app_kotlin.adapter.MyHandler
import com.example.mob_app_kotlin.databinding.FragmentListBinding
import com.example.mob_app_kotlin.model.Chat

class ListFragment : Fragment(), MyHandler {

    lateinit var binding: FragmentListBinding
    lateinit var myAdapter: MyAdapter
    var chats:ArrayList<Chat> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments?.getString("usr").toString() != null){
            var usr:String = arguments?.getString("usr").toString()

            myAdapter = MyAdapter()
            myAdapter.myHandler = this

            binding.rv.adapter = myAdapter
            binding.rv.layoutManager = LinearLayoutManager(context)
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
        Toast.makeText(context, "SMS: " + chat.message, Toast.LENGTH_LONG).show()
    }
}