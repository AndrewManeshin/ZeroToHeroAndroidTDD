package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class AddViewModel(
    private val repository: Repository.Add,
    private val liveDataWrapper: ListLiveDataWrapper.Add,
    private val clear: ClearViewModel,
    private val dispatcher: CoroutineDispatcher,
    private val dispatcherMain: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    fun add(value: String) {
        CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate).launch(dispatcher) {
            repository.add(value)
//            withContext(dispatcherMain) {
            liveDataWrapper.add(value)
            comeback()
//            }
        }
    }

    fun comeback() {
        clear.clearViewModel(this::class.java)
    }
}