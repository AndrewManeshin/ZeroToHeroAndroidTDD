package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.ViewModel

interface Module<T : ViewModel> {

    fun viewModel(): T
}