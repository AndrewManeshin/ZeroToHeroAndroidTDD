package ru.easycode.zerotoheroandroidtdd.main

import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.list.ListScreen

class MainViewModel(
    private val navigation: Navigation.Mutable
) : ViewModel() {

    fun navigateLiveData() = navigation.liveData()

    fun init(firstRun: Boolean) {
        if (firstRun)
            navigation.update(ListScreen)
    }
}