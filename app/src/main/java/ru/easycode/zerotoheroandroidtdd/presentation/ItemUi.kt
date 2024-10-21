package ru.easycode.zerotoheroandroidtdd.presentation

import android.widget.TextView

data class ItemUi(private val id: Long, private val text: String) {

    fun delete(deleteItemUi: DeleteItemUi) {
        deleteItemUi.delete(id)
    }

    fun show(textView: TextView) {
        textView.text = text
    }

    fun areItemsSame(other: ItemUi) = id == other.id
}

interface DeleteItemUi {

    fun delete(itemId: Long)
}