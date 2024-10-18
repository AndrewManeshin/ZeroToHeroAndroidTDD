package ru.easycode.zerotoheroandroidtdd.core

import android.os.Bundle

interface BundleWrapper {

    interface Save {
        fun save(list: ArrayList<CharSequence>)
    }

    interface Restore {
        fun restore(): List<CharSequence>
    }

    interface Mutable : Save, Restore

    class Base(private val bundle: Bundle) : Mutable {

        override fun save(list: ArrayList<CharSequence>) {
            bundle.putCharSequenceArrayList(KEY, list)
        }

        override fun restore(): List<CharSequence> {
            return bundle.getCharSequenceArrayList(KEY) ?: ArrayList()
        }

        private companion object {
            const val KEY = "CharSequenceArrayList"
        }
    }
}