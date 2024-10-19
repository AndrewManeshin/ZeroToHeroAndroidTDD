package ru.easycode.zerotoheroandroidtdd

import android.app.Application
import androidx.lifecycle.ViewModel

class App : Application(), ProvideViewModel {

    private lateinit var factory: ViewModelFactory

    override fun onCreate() {
        super.onCreate()

        val clearViewModel = object : ClearViewModel {
            override fun clearViewModel(clasz: Class<out ViewModel>) {
                factory.clearViewModel(clasz)
            }
        }

        val make = ProvideViewModel.Make(Core(clearViewModel, applicationContext))
        factory = ViewModelFactory.Base(make)
    }

    override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
        return factory.viewModel(viewModelClass)
    }
}