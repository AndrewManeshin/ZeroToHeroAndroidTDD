package ru.easycode.zerotoheroandroidtdd

import kotlinx.coroutines.delay

interface Repository {

    suspend fun load()

    class Mok : Repository{
        override suspend fun load() {
            delay(2000)
        }
    }
}