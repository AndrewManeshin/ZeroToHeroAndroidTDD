package ru.easycode.zerotoheroandroidtdd

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText

class ActionButton @JvmOverloads constructor (
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : androidx.appcompat.widget.AppCompatButton(context, attrs, defStyle), TextWatcher {

    fun onClick(textView: TextView, inputEditText: TextInputEditText) {
        setOnClickListener {
            textView.text = inputEditText.text
            inputEditText.text?.clear()
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

    override fun afterTextChanged(s: Editable?) {
        isEnabled = (s?.length ?: 0) > 2
    }
}