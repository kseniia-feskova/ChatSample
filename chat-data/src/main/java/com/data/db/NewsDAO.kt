package com.example.chatsample.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.domain.model.NewsItem
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface NewsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNews(countries: List<NewsItem>): Completable

    @Query("SELECT * FROM NewsDB")
    fun getNews(): Single<List<NewsItem>>

    @Query("DELETE FROM NewsDB")
    fun deleteAll(): Completable
}