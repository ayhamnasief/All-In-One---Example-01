package com.example.mob_app_kotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mob_app_kotlin.R
import com.example.mob_app_kotlin.model.Chat

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    var myHandler:MyHandler ?= null
    var chats:ArrayList<Chat> = ArrayList()

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var row_item_iv_image:ImageView = itemView.findViewById(R.id.row_item_iv_image)
        var row_item_tv_usr:TextView = itemView.findViewById(R.id.row_item_tv_usr)
        var row_item_tv_message:TextView = itemView.findViewById(R.id.row_item_tv_message)

        fun bind(chat:Chat){
            row_item_iv_image.setImageResource(chat.imageId)
            row_item_tv_usr.setText(chat.name)
            row_item_tv_message.setText(chat.message)
            itemView.setOnClickListener {
                myHandler?.handlerMethod(chat)
            }
        }
    }

    fun setChat(chats:ArrayList<Chat>){
        this.chats = chats
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view:View = LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return chats.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var chat:Chat = chats.get(position)
        holder.bind(chat)
    }
}