package ru.easycode.zerotoheroandroidtdd.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.addTextChangedListener
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentCreateBinding
import ru.easycode.zerotoheroandroidtdd.di.ProvideViewModel

class CreateFragment : AbstractFragment<FragmentCreateBinding>() {

    private lateinit var viewModel: CreateViewModel
    private val backPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() = viewModel.comeback()
    }

    override fun bind(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentCreateBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel =
            (requireActivity() as ProvideViewModel).viewModel(CreateViewModel::class.java)

        requireActivity().onBackPressedDispatcher.addCallback(backPressedCallback)

        binding.inputEditText.addTextChangedListener {
            binding.createButton.isEnabled = (binding.inputEditText.text.toString().length) > 2
        }

        binding.createButton.setOnClickListener {
            hideKeyBoard()
            viewModel.add(binding.inputEditText.text.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        backPressedCallback.remove()
    }
}