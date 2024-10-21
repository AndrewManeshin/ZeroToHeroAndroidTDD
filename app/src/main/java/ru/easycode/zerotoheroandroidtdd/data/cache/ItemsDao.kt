package ru.easycode.zerotoheroandroidtdd.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ItemsDao {

    @Query("SELECT * FROM items_entity")
    fun list(): List<ItemCache>

    @Query("SELECT * FROM items_entity WHERE id = :id")
    fun item(id: Long): ItemCache

    @Query("DELETE FROM items_entity WHERE id = :id")
    fun delete(id: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(item: ItemCache)
}