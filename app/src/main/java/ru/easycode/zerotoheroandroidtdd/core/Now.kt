package ru.easycode.zerotoheroandroidtdd.core

interface Now {

    fun nowMillis(): Long

    class Base : Now {
        override fun nowMillis() = System.currentTimeMillis()
    }
}
