package com.example.mob_app_kotlin.model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalRepositoryImp(private val db:ChatDatabase):LocalRepository {

    override suspend fun insertOrUpdateChat(chat: Chat) {
        withContext(Dispatchers.IO){
            db.chatDao().insertOrUpdateChat(chat)
        }
    }

    override suspend fun deleteChat(chat: Chat) {
        withContext(Dispatchers.IO) {
            db.chatDao().deleteChat(chat)
        }
    }

    override suspend fun getChats() = withContext(Dispatchers.IO){
            db.chatDao().getChats()
        }
    }