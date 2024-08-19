package com.example.mob_app_kotlin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mob_app_kotlin.R
import com.example.mob_app_kotlin.ui.adapter.MyAdapter
import com.example.mob_app_kotlin.ui.adapter.MyHandler
import com.example.mob_app_kotlin.databinding.FragmentListBinding
import com.example.mob_app_kotlin.model.Chat
import com.example.mob_app_kotlin.model.ChatDatabase
import com.example.mob_app_kotlin.model.LocalRepositoryImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import kotlinx.coroutines.withContext

class ListFragment : Fragment(), MyHandler {

    lateinit var binding: FragmentListBinding
    lateinit var myAdapter: MyAdapter
    lateinit var localRepositoryImp: LocalRepositoryImp
    var chats:List<Chat> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


            var usr:String = arguments?.getString("usr").toString()

            var db = ChatDatabase.getInstance(requireContext())
        localRepositoryImp = LocalRepositoryImp(db)

            myAdapter = MyAdapter()
            myAdapter.myHandler = this

            binding.rv.adapter = myAdapter
            binding.rv.layoutManager = LinearLayoutManager(context)
            binding.rv.setHasFixedSize(true)

        getAllChats()

            binding.btnSend.setOnClickListener {
                var message = binding.etMessage.text.toString()
                    GlobalScope.launch(Dispatchers.IO){
                        localRepositoryImp.insertOrUpdateChat(
                            Chat(0, usr, message, R.drawable.ic_sms)
                        )
                    }

                getAllChats()
                    binding.etMessage.setText("")
            }
    }

    fun getAllChats(){
        GlobalScope.launch(Dispatchers.IO){
            var returnedChats = async {
                localRepositoryImp.getChats()
            }
            withContext(Dispatchers.Main){
                binding.pb.visibility = View.VISIBLE
                chats = returnedChats.await()
                binding.pb.visibility = View.GONE
                myAdapter.setChat(chats)
            }
        }
    }

    override fun handlerMethod(chat: Chat) {
        GlobalScope.launch(Dispatchers.IO){
            localRepositoryImp.deleteChat(chat)
        }
        Toast.makeText(context, "This chat is deleted successfully: ", Toast.LENGTH_LONG).show()
        getAllChats()
    }
}