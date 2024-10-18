package ru.easycode.zerotoheroandroidtdd.list

import ru.easycode.zerotoheroandroidtdd.di.Core
import ru.easycode.zerotoheroandroidtdd.di.Module

class ListModule(private val core: Core) : Module<ListViewModel> {
    override fun viewModel() = ListViewModel(
        core.listLiveData,
        core.navigation
    )
}