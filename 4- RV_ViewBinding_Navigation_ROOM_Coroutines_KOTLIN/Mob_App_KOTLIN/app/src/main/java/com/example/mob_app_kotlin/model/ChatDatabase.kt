package com.example.mob_app_kotlin.model

import android.content.Context
import android.service.autofill.UserData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlin.concurrent.Volatile

private const val DATABASE_NAME = "user_database"
@Database(entities = [Chat::class], version = 1, exportSchema = false)
abstract class ChatDatabase:RoomDatabase() {
    abstract fun chatDao(): ChatDAO

    companion object{
        @Volatile
        private var instance:ChatDatabase ?= null

        fun getInstance(context: Context): ChatDatabase{
            return instance ?: synchronized(Any()){
                instance ?: buildDatabase(context).also{instance = it}
            }
        }

        private fun buildDatabase(context: Context):ChatDatabase{
            return Room.databaseBuilder(
                context.applicationContext,
                ChatDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}