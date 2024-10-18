package ru.easycode.zerotoheroandroidtdd.list

import ru.easycode.zerotoheroandroidtdd.di.AbstractProvideViewModel
import ru.easycode.zerotoheroandroidtdd.di.Core
import ru.easycode.zerotoheroandroidtdd.di.Module
import ru.easycode.zerotoheroandroidtdd.di.ProvideViewModel

class ProvideListViewModel(core: Core, nextChain: ProvideViewModel) :
    AbstractProvideViewModel(core, nextChain, ListViewModel::class.java) {

    override fun model(): Module<*> = ListModule(core)
}