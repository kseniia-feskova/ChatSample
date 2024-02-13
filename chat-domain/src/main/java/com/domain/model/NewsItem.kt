package com.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity(tableName = "NewsDB")
data class NewsItem(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
    val url: String,
    val author: String,
    val image: String?,
    val language: String,
    val category: List<String>,
    val published: String
)

class Converters {
    @TypeConverter
    fun fromString(value: String): List<String> {
        return value.split(",")
    }

    @TypeConverter
    fun listToString(list: List<String>): String {
        return list.joinToString(",")
    }
}