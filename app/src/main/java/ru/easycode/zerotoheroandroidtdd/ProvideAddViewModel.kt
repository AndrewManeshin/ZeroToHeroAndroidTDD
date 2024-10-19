package ru.easycode.zerotoheroandroidtdd

class ProvideAddViewModel(core: Core, nextChain: ProvideViewModel) :
    AbstractProvideViewModel(core, nextChain, AddViewModel::class.java) {

    override fun model(): Module<*> = AddModule(core)
}

class AddModule(private val core: Core) : Module<AddViewModel> {

    override fun viewModel() = AddViewModel(
        core.repository,
        core.listLiveData,
        core.clearViewModel,
        core.dispatcher
    )
}