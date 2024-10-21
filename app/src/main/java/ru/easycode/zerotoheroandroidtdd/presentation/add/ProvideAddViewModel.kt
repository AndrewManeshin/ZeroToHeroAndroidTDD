package ru.easycode.zerotoheroandroidtdd.presentation.add

import ru.easycode.zerotoheroandroidtdd.di.AbstractProvideViewModel
import ru.easycode.zerotoheroandroidtdd.di.Core
import ru.easycode.zerotoheroandroidtdd.di.Module
import ru.easycode.zerotoheroandroidtdd.di.ProvideViewModel

class ProvideAddViewModel(core: Core, nextChain: ProvideViewModel) :
    AbstractProvideViewModel(core, nextChain, AddViewModel::class.java) {

    override fun model(): Module<*> = AddModule(core)
}

class AddModule(private val core: Core) : Module<AddViewModel> {

    override fun viewModel() = AddViewModel(
        core.repository,
        core.listLiveData,
        core.clearViewModel,
        core.dispatcher,
        core.dispatcherMain
    )
}