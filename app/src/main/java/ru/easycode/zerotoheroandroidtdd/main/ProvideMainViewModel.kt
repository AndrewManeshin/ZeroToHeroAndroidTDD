package ru.easycode.zerotoheroandroidtdd.main

import ru.easycode.zerotoheroandroidtdd.di.AbstractProvideViewModel
import ru.easycode.zerotoheroandroidtdd.di.Core
import ru.easycode.zerotoheroandroidtdd.di.Module
import ru.easycode.zerotoheroandroidtdd.di.ProvideViewModel

class ProvideMainViewModel(core: Core, nextChain: ProvideViewModel) :
    AbstractProvideViewModel(core, nextChain, MainViewModel::class.java) {

    override fun model(): Module<*> = MainModule(core)
}