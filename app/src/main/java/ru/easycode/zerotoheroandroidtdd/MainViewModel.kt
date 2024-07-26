package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val liveDataWrapper: LiveDataWrapper,
    private val repository: Repository
) {

    private val viewModelScoupe = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    fun load() {
        liveDataWrapper.update(UiState.ShowProgress)
        viewModelScoupe.launch {
            repository.load()
            withContext(Dispatchers.Main) {
                liveDataWrapper.update(UiState.ShowData)
            }
        }
    }

    fun observe(lifecycleOwner: LifecycleOwner, block: (state: UiState) -> Unit) {
        liveDataWrapper.liveData().observe(lifecycleOwner) {
            liveDataWrapper.liveData().value?.let { value -> block.invoke(value) }
        }
    }
}