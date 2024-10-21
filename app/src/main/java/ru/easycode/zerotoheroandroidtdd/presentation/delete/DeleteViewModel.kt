package ru.easycode.zerotoheroandroidtdd.presentation.delete

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineDispatcher
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModel
import ru.easycode.zerotoheroandroidtdd.core.MyViewModel
import ru.easycode.zerotoheroandroidtdd.data.Repository
import ru.easycode.zerotoheroandroidtdd.presentation.ItemUi
import ru.easycode.zerotoheroandroidtdd.presentation.main.ListLiveDataWrapper

class DeleteViewModel(
    private val deleteLiveDataWrapper: ListLiveDataWrapper.Delete,
    private val repository: Repository.Delete,
    clear: ClearViewModel,
    dispatcher: CoroutineDispatcher,
    dispatcherMain: CoroutineDispatcher
) : MyViewModel.Abstract.Clear(clear, dispatcher, dispatcherMain, DeleteViewModel::class.java) {

    private val innerLiveData = MutableLiveData<String>()
    val liveData: LiveData<String>
        get() = innerLiveData

    fun init(itemId: Long) {
        handleAsync({
            repository.item(itemId).text
        }) {
            innerLiveData.value = it
        }
    }

    fun delete(itemId: Long) {
        handleAsync({
            repository.delete(itemId)
        }) {
            deleteLiveDataWrapper.delete(ItemUi(itemId, liveData.value ?: ""))
            comeback()
        }
    }
}