package ru.easycode.zerotoheroandroidtdd

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ItemsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(item: ItemCache)

    @Query("SELECT * FROM items_table")
    fun list(): List<ItemCache>
}