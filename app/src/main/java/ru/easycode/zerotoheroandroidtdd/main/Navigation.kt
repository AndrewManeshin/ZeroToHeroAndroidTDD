package ru.easycode.zerotoheroandroidtdd.main

import ru.easycode.zerotoheroandroidtdd.core.LiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.core.Screen

interface Navigation {

    interface Update : LiveDataWrapper.Update<Screen>

    interface Mutable : LiveDataWrapper.Mutable<Screen>, Update

    class Base : LiveDataWrapper.Abstract<Screen>(), Mutable
}