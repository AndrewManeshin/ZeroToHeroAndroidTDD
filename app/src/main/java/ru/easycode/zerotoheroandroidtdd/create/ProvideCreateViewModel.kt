package ru.easycode.zerotoheroandroidtdd.create

import ru.easycode.zerotoheroandroidtdd.di.AbstractProvideViewModel
import ru.easycode.zerotoheroandroidtdd.di.Core
import ru.easycode.zerotoheroandroidtdd.di.Module
import ru.easycode.zerotoheroandroidtdd.di.ProvideViewModel

class ProvideCreateViewModel(core: Core, nextChain: ProvideViewModel) :
    AbstractProvideViewModel(core, nextChain, CreateViewModel::class.java) {

    override fun model(): Module<*> = CreateModule(core)
}