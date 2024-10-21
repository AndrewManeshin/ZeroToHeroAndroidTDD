package ru.easycode.zerotoheroandroidtdd.presentation.main

import ru.easycode.zerotoheroandroidtdd.di.AbstractProvideViewModel
import ru.easycode.zerotoheroandroidtdd.di.Core
import ru.easycode.zerotoheroandroidtdd.di.Module
import ru.easycode.zerotoheroandroidtdd.di.ProvideViewModel

class ProvideMainViewModel(core: Core, nextChain: ProvideViewModel) :
    AbstractProvideViewModel(core, nextChain, MainViewModel::class.java) {

    override fun model(): Module<*> = DeleteModule(core)
}

class DeleteModule(private val core: Core) : Module<MainViewModel> {

    override fun viewModel() = MainViewModel(
        core.repository,
        core.listLiveData,
        core.dispatcher,
        core.dispatcherMain
    )
}