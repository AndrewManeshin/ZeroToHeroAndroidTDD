package ru.easycode.zerotoheroandroidtdd.di

import ru.easycode.zerotoheroandroidtdd.core.ClearViewModel
import ru.easycode.zerotoheroandroidtdd.list.ListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.main.Navigation

class Core(val clear: ClearViewModel) {

    val listLiveData = ListLiveDataWrapper.Base()
    val navigation = Navigation.Base()
}