package com.example.chatsample.domain.repository

import com.example.chatsample.domain.model.UserData

interface IUserRepository {
    suspend fun getUserOrNull(id: String): UserData?
    suspend fun setUser(user: UserData)
    suspend fun checkFreeName(name: String): Boolean
    suspend fun getUserByName(name: String): UserData?
    fun saveUsersIdLocally(id: String)
    fun getLoggedId(): String
}