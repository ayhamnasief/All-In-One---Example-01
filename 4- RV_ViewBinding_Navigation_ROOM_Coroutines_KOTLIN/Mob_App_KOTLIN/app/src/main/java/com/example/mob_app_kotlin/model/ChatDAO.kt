package com.example.mob_app_kotlin.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ChatDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateChat(chat:Chat)

    @Delete
    suspend fun deleteChat(chat:Chat)

    @Query("SELECT * FROM chat_table")
    suspend fun getChats():List<Chat>
}