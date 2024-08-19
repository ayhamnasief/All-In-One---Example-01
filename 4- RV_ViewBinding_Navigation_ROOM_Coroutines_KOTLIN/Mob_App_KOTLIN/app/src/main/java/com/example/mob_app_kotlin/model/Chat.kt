package com.example.mob_app_kotlin.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chat_table")
data class Chat(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo("name")
    var name:String,
    var message:String,
    var imageId:Int
)