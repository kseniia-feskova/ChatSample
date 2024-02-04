package com.example.chatsample.data.repository

import com.example.chatsample.data.prefs.UserPreferences
import com.example.chatsample.data.utils.encrypt
import com.example.chatsample.domain.model.UserData
import com.example.chatsample.domain.repository.IUserRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UsersRepository @Inject constructor(private val usersPreferences: UserPreferences) : IUserRepository {

    private val collection = FirebaseFirestore.getInstance().collection("users")
    override suspend fun getUserOrNull(id: String): UserData? {
        val document = collection.document(id).get().await()
        return if (document != null) {
            val userData = document.toObject(UserData::class.java)
            userData
        } else null
    }

    override suspend fun setUser(user: UserData) {
        collection.add(user).await()
    }

    override suspend fun checkFreeName(name: String): Boolean {
        val users = collection.whereEqualTo("name", encrypt(name)).get().await().documents
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
}