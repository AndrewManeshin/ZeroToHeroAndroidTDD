package ru.easycode.zerotoheroandroidtdd.presentation.main

import kotlinx.coroutines.CoroutineDispatcher
import ru.easycode.zerotoheroandroidtdd.core.MyViewModel
import ru.easycode.zerotoheroandroidtdd.data.Repository
import ru.easycode.zerotoheroandroidtdd.presentation.ItemUi

class MainViewModel(
    private val repository: Repository.Read,
    private val liveDataWrapper: ListLiveDataWrapper.Mutable,
    dispatcher: CoroutineDispatcher,
    dispatcherMain: CoroutineDispatcher,
) : MyViewModel.Abstract(dispatcher, dispatcherMain) {

    fun init() {
        handleAsync({
            repository.list().map { ItemUi(it.id, it.text) }
        }) {
            liveDataWrapper.update(it)
        }
    }

    fun liveData() = liveDataWrapper.liveData()
}