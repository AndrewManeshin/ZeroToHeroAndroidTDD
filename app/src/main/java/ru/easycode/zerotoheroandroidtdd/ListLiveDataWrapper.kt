package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface ListLiveDataWrapper {

    interface Read {
        fun liveData(): LiveData<List<String>>
    }

    interface Update {
        fun update(value: List<String>)
    }

    interface Mutable : Read, Update

    interface Add {
        fun add(value: String)
    }

    interface All : Add, Mutable

    class Base : All {

        private val liveData = MutableLiveData<List<String>>()

        override fun liveData(): LiveData<List<String>> = liveData

        override fun update(value: List<String>) = liveData.postValue(value)

        override fun add(value: String) {
            val currentList = liveData.value?.toMutableList() ?: mutableListOf()
            currentList.add(value)
            update(currentList)
        }
    }
}