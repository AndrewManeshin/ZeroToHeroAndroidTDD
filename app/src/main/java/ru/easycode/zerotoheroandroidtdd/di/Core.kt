package ru.easycode.zerotoheroandroidtdd.di

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModel
import ru.easycode.zerotoheroandroidtdd.core.Now
import ru.easycode.zerotoheroandroidtdd.data.Repository
import ru.easycode.zerotoheroandroidtdd.data.cache.ItemsDataBase
import ru.easycode.zerotoheroandroidtdd.presentation.main.ListLiveDataWrapper

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
    val dispatcherMain = Dispatchers.Main

    fun dao() = database.itemsDao()
}