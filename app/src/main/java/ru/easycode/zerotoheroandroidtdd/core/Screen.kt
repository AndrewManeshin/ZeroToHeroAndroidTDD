package ru.easycode.zerotoheroandroidtdd.core

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

interface Screen {

    fun show(@IdRes containerId: Int, fragmentManager: FragmentManager)

    abstract class Replace(private val fragment: Class<out Fragment>) : Screen {

        override fun show(containerId: Int, fragmentManager: FragmentManager) {
            fragmentManager.beginTransaction()
                .replace(containerId, fragment.getDeclaredConstructor().newInstance())
                .commit()
        }
    }

    abstract class Add(private val fragmentClass: Class<out Fragment>) : Screen {

        override fun show(containerId: Int, fragmentManager: FragmentManager) {
            fragmentManager.beginTransaction()
                .add(containerId, fragmentClass.getDeclaredConstructor().newInstance())
                .addToBackStack(fragmentClass.name)
                .commit()
        }
    }

    object Pop : Screen {

        override fun show(containerId: Int, fragmentManager: FragmentManager) {
            fragmentManager.popBackStack()
        }
    }
}