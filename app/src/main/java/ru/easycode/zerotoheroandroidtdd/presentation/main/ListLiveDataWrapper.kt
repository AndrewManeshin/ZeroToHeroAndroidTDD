package ru.easycode.zerotoheroandroidtdd.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.presentation.ItemUi

interface ListLiveDataWrapper {

    interface Read {
        fun liveData(): LiveData<List<ItemUi>>
    }

    interface Update {
        fun update(value: List<ItemUi>)
    }

    interface Mutable : Read, Update

    interface Add {
        fun add(value: ItemUi)
    }

    interface Delete {
        fun delete(item: ItemUi)
    }

    interface All : Add, Mutable, Delete

    class Base : All {

        private val liveData = MutableLiveData<List<ItemUi>>()

        override fun liveData(): LiveData<List<ItemUi>> = liveData

        override fun update(value: List<ItemUi>) = liveData.postValue(value)

        override fun add(value: ItemUi) {
            val currentList = liveData.value?.toMutableList() ?: mutableListOf()
            currentList.add(value)
            update(currentList)
        }

        override fun delete(item: ItemUi) {
            val currentList = liveData.value?.toMutableList() ?: mutableListOf()
            currentList.remove(item)
            update(currentList)
        }
    }
}