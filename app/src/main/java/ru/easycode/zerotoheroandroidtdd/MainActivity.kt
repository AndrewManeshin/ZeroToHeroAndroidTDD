package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    val count = Count.Base(2, 4, 0)
    private lateinit var decrementButton: Button
    private lateinit var incrementButton: Button
    private lateinit var textView: TextView
    private var state: UiState = count.initial("0")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        incrementButton = findViewById(R.id.incrementButton)
        decrementButton = findViewById(R.id.decrementButton)
        textView = findViewById(R.id.countTextView)

        state.apply(textView, incrementButton, decrementButton)

        decrementButton.setOnClickListener {
            state = count.decrement(textView.text.toString())
            state.apply(textView, incrementButton, decrementButton)
        }

        incrementButton.setOnClickListener {
            state = count.increment(textView.text.toString())
            state.apply(textView, incrementButton, decrementButton)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY, state)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            savedInstanceState.getSerializable(KEY, UiState::class.java) as UiState
        } else {
            savedInstanceState.getSerializable(KEY) as UiState
        }

        state.apply(textView, incrementButton, decrementButton)
    }

    companion object {
        private const val KEY = "UiStateKey"
    }
}