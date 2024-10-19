package ru.easycode.zerotoheroandroidtdd

class ProvideMainViewModel(core: Core, nextChain: ProvideViewModel) :
    AbstractProvideViewModel(core, nextChain, MainViewModel::class.java) {

    override fun model(): Module<*> = MainModule(core)
}

class MainModule(private val core: Core) : Module<MainViewModel> {

    override fun viewModel() = MainViewModel(
        core.repository,
        core.listLiveData,
        core.dispatcher
    )
}