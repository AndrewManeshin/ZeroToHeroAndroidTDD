package ru.easycode.zerotoheroandroidtdd.presentation.delete

import ru.easycode.zerotoheroandroidtdd.di.AbstractProvideViewModel
import ru.easycode.zerotoheroandroidtdd.di.Core
import ru.easycode.zerotoheroandroidtdd.di.Module
import ru.easycode.zerotoheroandroidtdd.di.ProvideViewModel

class ProvideDeleteViewModel(core: Core, nextChain: ProvideViewModel) :
    AbstractProvideViewModel(core, nextChain, DeleteViewModel::class.java) {

    override fun model(): Module<*> = DeleteModule(core)
}

class DeleteModule(private val core: Core) : Module<DeleteViewModel> {

    override fun viewModel() = DeleteViewModel(
        core.listLiveData,
        core.repository,
        core.clearViewModel,
        core.dispatcher,
        core.dispatcherMain
    )
}