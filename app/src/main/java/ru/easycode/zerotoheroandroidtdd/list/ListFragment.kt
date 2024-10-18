package ru.easycode.zerotoheroandroidtdd.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.core.BundleWrapper
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentListBinding
import ru.easycode.zerotoheroandroidtdd.di.ProvideViewModel

class ListFragment : AbstractFragment<FragmentListBinding>() {

    private val viewModel: ListViewModel by lazy {
        (requireActivity() as ProvideViewModel).viewModel(ListViewModel::class.java)
    }

    override fun bind(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentListBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TextAdapter()
        binding.recyclerView.adapter = adapter

        binding.addButton.setOnClickListener {
            viewModel.create()
        }

        viewModel.liveData().observe(viewLifecycleOwner) {
            adapter.update(it)
        }

        viewModel.liveData().value?.let { adapter.update(it) }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.save(BundleWrapper.Base(outState))
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.let { viewModel.restore(BundleWrapper.Base(it)) }
    }
}