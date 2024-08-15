package com.aleksandrgenrikhs.ideaplatformtesttask.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "item")
data class ProductEntry(
    @PrimaryKey
    val id: Int,
    val name: String,
    val time: Long,
    @TypeConverters(Converters::class) val tags: List<String>,
    val amount: Int,
)