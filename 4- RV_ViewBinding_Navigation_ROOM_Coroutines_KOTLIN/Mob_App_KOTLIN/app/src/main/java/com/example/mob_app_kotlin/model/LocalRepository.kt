package com.example.mob_app_kotlin.model

import androidx.room.Delete
import androidx.room.Query

interface LocalRepository {

    suspend fun insertOrUpdateChat(chat:Chat)

    suspend fun deleteChat(chat:Chat)

    suspend fun getChats():List<Chat>

}