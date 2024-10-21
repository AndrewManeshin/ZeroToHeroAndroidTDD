package ru.easycode.zerotoheroandroidtdd.presentation.add

import kotlinx.coroutines.CoroutineDispatcher
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModel
import ru.easycode.zerotoheroandroidtdd.core.MyViewModel
import ru.easycode.zerotoheroandroidtdd.data.Repository
import ru.easycode.zerotoheroandroidtdd.presentation.ItemUi
import ru.easycode.zerotoheroandroidtdd.presentation.main.ListLiveDataWrapper

class AddViewModel(
    private val repository: Repository.Add,
    private val liveDataWrapper: ListLiveDataWrapper.Add,
    clear: ClearViewModel,
    dispatcher: CoroutineDispatcher,
    dispatcherMain: CoroutineDispatcher
) : MyViewModel.Abstract.Clear(clear, dispatcher, dispatcherMain, AddViewModel::class.java) {

    fun add(value: String) {
        handleAsync({
            repository.add(value)
        }) {
            liveDataWrapper.add(ItemUi(it, value))
            comeback()
        }
    }
}