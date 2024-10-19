package ru.easycode.zerotoheroandroidtdd

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.Dispatchers

class Core(
    val clearViewModel: ClearViewModel,
    private val context: Context
) {

    private val database by lazy {
        Room.databaseBuilder(
            context,
            ItemsDataBase::class.java,
            "items_database"
        ).build()
    }

    val repository = Repository.Base(dao(), Now.Base())
    val listLiveData = ListLiveDataWrapper.Base()
    val dispatcher = Dispatchers.IO

    fun dao() = database.itemsDao()
}