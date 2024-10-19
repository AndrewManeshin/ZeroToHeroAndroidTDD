package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.ViewModel

interface ProvideViewModel {

    fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T

    class Make(core: Core) : ProvideViewModel {

        private var chain: ProvideViewModel

        init {
            chain = Error()
            chain = ProvideAddViewModel(core, chain)
            chain = ProvideMainViewModel(core, chain)
        }

        override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T =
            chain.viewModel(viewModelClass)
    }

    class Error : ProvideViewModel {
        override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T =
            throw IllegalStateException("unknown class $viewModelClass")
    }
}

@Suppress("UNCHECKED_CAST")
abstract class AbstractProvideViewModel(
    protected val core: Core,
    private val nextChain: ProvideViewModel,
    private val viewModelClass: Class<out ViewModel>
) : ProvideViewModel {

    override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
        return if (viewModelClass == this.viewModelClass) {
            model().viewModel() as T
        } else
            nextChain.viewModel(viewModelClass)
    }

    protected abstract fun model(): Module<*>
}