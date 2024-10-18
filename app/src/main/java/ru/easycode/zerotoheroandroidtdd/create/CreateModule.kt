package ru.easycode.zerotoheroandroidtdd.create

import ru.easycode.zerotoheroandroidtdd.di.Core
import ru.easycode.zerotoheroandroidtdd.di.Module

class CreateModule(private val core: Core) : Module<CreateViewModel> {

    override fun viewModel() = CreateViewModel(
        core.listLiveData,
        core.navigation,
        core.clear
    )
}