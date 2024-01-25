package com.example.chatsample.data

import com.example.chatsample.domain.repository.IUserRepository
import com.example.chatsample.domain.model.UserData
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UsersRepository() : IUserRepository {

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
        val users = collection.whereEqualTo("name", name).get().await().documents
        return users.isEmpty()
    }
}