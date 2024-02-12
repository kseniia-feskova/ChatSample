package com.example.chatsample.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.chatsample.data.model.Converters
import com.example.chatsample.data.model.NewsItem

@Database(entities = [NewsItem::class], version = 1)
@TypeConverters(Converters::class)
abstract class NewsDB : RoomDatabase() {

    abstract fun getNewsDAO() : NewsDAO

}