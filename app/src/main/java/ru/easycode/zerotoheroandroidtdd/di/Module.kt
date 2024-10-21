package ru.easycode.zerotoheroandroidtdd.di

import androidx.lifecycle.ViewModel

interface Module<T : ViewModel> {

    fun viewModel(): T
}