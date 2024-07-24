package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.telephony.ServiceState
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private lateinit var linearLayout: LinearLayout
    private lateinit var textView: TextView
    private var state: State = State.Initial

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayout = findViewById(R.id.rootLayout)
        textView = findViewById(R.id.titleTextView)
        val button = findViewById<Button>(R.id.removeButton)

        button.setOnClickListener {
            state = State.Removed
            state.apply(linearLayout, textView)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY, state)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            savedInstanceState.getSerializable(KEY, State::class.java) as State
        } else {
            savedInstanceState.getSerializable(KEY) as State
        }
        state.apply(linearLayout, textView)
    }

    companion object {
        private const val KEY = "key"
    }
}

interface State : Serializable {

    fun apply(linearLayout: LinearLayout, textView: TextView)

    object Initial : State {
        override fun apply(linearLayout: LinearLayout, textView: TextView) = Unit
    }

    object Removed : State {
        override fun apply(linearLayout: LinearLayout, textView: TextView) {
            linearLayout.removeView(textView)
        }
    }
}