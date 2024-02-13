package com.example.chatsample.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.domain.model.Converters
import com.domain.model.NewsItem

@Database(entities = [NewsItem::class], version = 1)
@TypeConverters(Converters::class)
abstract class NewsDB : RoomDatabase() {

    abstract fun getNewsDAO() : NewsDAO

}