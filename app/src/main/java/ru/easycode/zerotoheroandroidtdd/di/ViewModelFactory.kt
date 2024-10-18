package ru.easycode.zerotoheroandroidtdd.di

import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModel

interface ViewModelFactory : ClearViewModel {

    fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T

    @Suppress("UNCHECKED_CAST")
    class Base(
        private val provideViewModel: ProvideViewModel
    ) : ViewModelFactory {

        private val viewModelMap: MutableMap<Class<out ViewModel>, ViewModel> = mutableMapOf()

        override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
            var viewModel = viewModelMap[viewModelClass]
            return if (viewModel == null) {
                viewModel = provideViewModel.viewModel(viewModelClass)
                viewModelMap[viewModelClass] = viewModel
                viewModel
            } else
                viewModel as T
        }

        override fun clear(viewModelClass: Class<out ViewModel>) {
            viewModelMap.remove(viewModelClass)
        }
    }
}