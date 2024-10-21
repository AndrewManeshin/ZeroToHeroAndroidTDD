package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

interface MyViewModel {

    abstract class Abstract(
        private val dispatcher: CoroutineDispatcher,
        private val dispatcherMain: CoroutineDispatcher
    ) : ViewModel(), MyViewModel {

        protected val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

        protected fun <T : Any> handleAsync(
            heavyOperation: suspend () -> T,
            uiUpdate: (T) -> Unit
        ) {
            viewModelScope.launch(dispatcher) {
                val result = heavyOperation.invoke()
                withContext(dispatcherMain) {
                    uiUpdate.invoke(result)
                }
            }
        }

        abstract class Clear(
            private val clear: ClearViewModel,
            dispatcher: CoroutineDispatcher,
            dispatcherMain: CoroutineDispatcher,
            private val viewModelClass: Class<out ViewModel>
        ) : Abstract(dispatcher, dispatcherMain) {

            fun comeback() {
                clear.clearViewModel(viewModelClass)
            }
        }
    }
}