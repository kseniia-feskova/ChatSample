package com.example.chatsample.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.chatsample.data.model.NewsItem

@Dao
interface NewsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNews(countries: List<NewsItem>)

    @Query("SELECT * FROM NewsDB")
    fun getNews(): List<NewsItem>

    @Query("DELETE FROM NewsDB")
    fun deleteAll()
}