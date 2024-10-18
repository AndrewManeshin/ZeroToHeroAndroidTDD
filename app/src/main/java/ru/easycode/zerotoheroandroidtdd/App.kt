package ru.easycode.zerotoheroandroidtdd

import android.app.Application
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModel
import ru.easycode.zerotoheroandroidtdd.di.Core
import ru.easycode.zerotoheroandroidtdd.di.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.di.ViewModelFactory

class App : Application(), ProvideViewModel {

    private lateinit var factory: ViewModelFactory

    override fun onCreate() {
        super.onCreate()

        val clearViewModel = object : ClearViewModel {
            override fun clear(viewModelClass: Class<out ViewModel>) {
                factory.clear(viewModelClass)
            }
        }

        val make = ProvideViewModel.Make(Core(clearViewModel))
        factory = ViewModelFactory.Base(make)
    }

    override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
        return factory.viewModel(viewModelClass)
    }
}