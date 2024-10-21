package ru.easycode.zerotoheroandroidtdd.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding
import ru.easycode.zerotoheroandroidtdd.di.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.presentation.DeleteItemUi
import ru.easycode.zerotoheroandroidtdd.presentation.add.AddBottomSheetFragment
import ru.easycode.zerotoheroandroidtdd.presentation.delete.DeleteBottomSheetFragment

class MainActivity : AppCompatActivity(), ProvideViewModel {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = viewModel(MainViewModel::class.java)

        val adapter = Adapter(object : DeleteItemUi {
            override fun delete(itemId: Long) {
                DeleteBottomSheetFragment.newInstance(itemId)
                    .show(supportFragmentManager, "CreateDeleteBottomSheetFragment")
            }
        })

        binding.recyclerView.adapter = adapter

        viewModel.liveData().observe(this) { data ->
            adapter.update(data)
        }

        binding.addButton.setOnClickListener {
            AddBottomSheetFragment().show(supportFragmentManager, "CreateBottomSheetFragment")
        }

        viewModel.init()
    }

    override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T =
        (application as ProvideViewModel).viewModel(viewModelClass)
}