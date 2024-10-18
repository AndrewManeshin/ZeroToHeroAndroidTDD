package ru.easycode.zerotoheroandroidtdd.main

import ru.easycode.zerotoheroandroidtdd.di.Core
import ru.easycode.zerotoheroandroidtdd.di.Module

class MainModule(private val core: Core) : Module<MainViewModel> {

    override fun viewModel() = MainViewModel(
        core.navigation
    )
}