package ru.easycode.zerotoheroandroidtdd.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.R
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding
import ru.easycode.zerotoheroandroidtdd.di.ProvideViewModel

class MainActivity : AppCompatActivity(), ProvideViewModel {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = viewModel(MainViewModel::class.java)
        viewModel.init(savedInstanceState == null)

        viewModel.navigateLiveData().observe(this) { screen ->
            screen.show(R.id.mainContainer, supportFragmentManager)
        }
    }

    override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T =
        (application as ProvideViewModel).viewModel(viewModelClass)
}