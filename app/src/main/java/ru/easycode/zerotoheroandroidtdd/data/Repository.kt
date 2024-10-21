package ru.easycode.zerotoheroandroidtdd.data

import ru.easycode.zerotoheroandroidtdd.core.Now
import ru.easycode.zerotoheroandroidtdd.data.cache.ItemCache
import ru.easycode.zerotoheroandroidtdd.data.cache.ItemsDao

interface Repository {

    interface Read {
        fun list(): List<Item>
    }

    interface Add {
        fun add(value: String): Long
    }

    interface Delete {
        fun item(id: Long): Item

        fun delete(id: Long)
    }

    interface All : Read, Add, Delete

    class Base(
        private val dataSource: ItemsDao,
        private val now: Now
    ) : All {

        override fun list() = dataSource.list().map { Item(it.id, it.text) }

        override fun delete(id: Long) = dataSource.delete(id)

        override fun add(value: String): Long {
            val id = now.nowMillis()
            dataSource.add(ItemCache(id, value))
            return id
        }

        override fun item(id: Long): Item {
            val data = dataSource.item(id)
            return Item(data.id, data.text)
        }
    }
}