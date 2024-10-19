package ru.easycode.zerotoheroandroidtdd

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("items_table")
data class ItemCache(
    @PrimaryKey
    @ColumnInfo("id")
    val id: Long = -1,
    @ColumnInfo("text")
    val text: String
)