package com.example.chatsample.data.repository

import android.util.Log
import com.example.chatsample.data.prefs.UserPreferences
import com.domain.model.UserData
import com.domain.repository.IUserRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UsersRepository @Inject constructor(private val usersPreferences: UserPreferences) :
    IUserRepository {

    private val collection = FirebaseFirestore.getInstance().collection("users")
    override suspend fun getUserOrNull(id: String): UserData? {
        val document = collection.document(id).get().await()
        return if (document != null) {
            val userData = document.toObject(UserData::class.java)
            userData
        } else null
    }

    override suspend fun setUser(user: UserData) {
        collection.document(user.id).set(user).await()
    }

    override suspend fun checkFreeName(name: String): Boolean {
        val users = collection.whereEqualTo("name", name).get().await().documents
        return users.isEmpty()
    }

    override suspend fun getUserByName(name: String): UserData? {
        val documents = collection.whereEqualTo("name", name).get().await().documents
        return if (documents.isNotEmpty()) {
            val userData = documents.first().toObject(UserData::class.java)
            userData
        } else null
    }

    override fun saveUsersIdLocally(id: String) {
        usersPreferences.loggedId = id
    }

    override fun getLoggedId(): String {
        return usersPreferences.loggedId
    }

    override suspend fun deleteChat(userId: String, chatId: String) {
        val user = getUserOrNull(userId)
        if (user != null) {
            val oldChats = user.chats.toMutableMap()
            oldChats.remove(chatId)
            collection.document(user.id).update("chats", oldChats).addOnSuccessListener {
                Log.e("deleteChat", "for $chatId")
            }.await()
        }
    }

    override suspend fun getNewCompanions(): List<UserData> {
        val documents = collection.get().await().documents.filter { it.id != getLoggedId() }
        val currentChats = getUserOrNull(getLoggedId())?.chats?.keys
        return if (documents.isEmpty()) emptyList()
        else documents.mapNotNull { it.toObject(UserData::class.java) }
            .filter { !it.chats.keys.any { currentChats?.contains(it) == true } }
    }

    override suspend fun updateUnreadChat(userId: String?, chatId: String, isRead: Boolean) {
        val user = if (userId == null) {
            getUserOrNull(getLoggedId())
        } else getUserOrNull(userId)
        if (user != null) {
            val oldChats = user.chats.toMutableMap()
            oldChats[chatId] = isRead
            collection.document(user.id).update("chats", oldChats).addOnSuccessListener {
                Log.e("updateUnreadChat", "for $chatId")
            }.await()
        }
    }
}