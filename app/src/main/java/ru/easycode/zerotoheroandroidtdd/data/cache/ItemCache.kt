package ru.easycode.zerotoheroandroidtdd.data.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items_entity")
data class ItemCache(
    @PrimaryKey()
    @ColumnInfo("id")
    val id: Long,
    @ColumnInfo("text")
    val text: String
)