package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val titleTextView = findViewById<TextView>(R.id.titleTextView)

        if(savedInstanceState != null) titleTextView.text = savedInstanceState.getString("title_text")

        findViewById<Button>(R.id.changeButton).setOnClickListener {
            titleTextView.text =
                if (titleTextView.text == getString(R.string.hello_world))
                    getString(R.string.i_am_an_android_developer)
                else
                    getString(R.string.hello_world)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("title_text", findViewById<TextView>(R.id.titleTextView).text.toString())
        super.onSaveInstanceState(outState)
    }
}