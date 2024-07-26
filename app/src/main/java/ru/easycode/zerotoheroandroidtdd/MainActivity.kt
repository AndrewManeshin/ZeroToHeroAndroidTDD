package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.time.Duration

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.actionButton)
        val progress = findViewById<ProgressBar>(R.id.progressBar)
        val textView = findViewById<TextView>(R.id.titleTextView)

        button.setOnClickListener {

            textView.visibility = View.GONE
            progress.visibility = View.VISIBLE
            button.isEnabled = false

            button.postDelayed({
                textView.visibility = View.VISIBLE
                progress.visibility = View.GONE
                button.isEnabled = true
            }, 500)
        }
    }
}