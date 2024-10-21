package ru.easycode.zerotoheroandroidtdd.presentation.delete

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.easycode.zerotoheroandroidtdd.R
import ru.easycode.zerotoheroandroidtdd.di.ProvideViewModel

class DeleteBottomSheetFragment : BottomSheetDialogFragment(R.layout.layout_delete) {

    private lateinit var viewModel: DeleteViewModel
    private lateinit var onBackPressedCallback: OnBackPressedCallback

    companion object {
        fun newInstance(itemId: Long): DeleteBottomSheetFragment {
            val instance = DeleteBottomSheetFragment()
            instance.arguments = Bundle().apply {
                putLong(KEY, itemId)
            }
            return instance
        }

        private const val KEY = "DELETE_KEY"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        viewModel = (activity as ProvideViewModel).viewModel(DeleteViewModel::class.java)
        onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.comeback()
                dismiss()
            }
        }
        (dialog as BottomSheetDialog).onBackPressedDispatcher.addCallback(onBackPressedCallback)
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemId = requireArguments().getLong(KEY)

        view.findViewById<View>(R.id.deleteButton).setOnClickListener {
            viewModel.delete(itemId)
            dismiss()
        }

        val textView = view.findViewById<TextView>(R.id.itemTitleTextView)

        viewModel.liveData.observe(this) { text ->
            textView.text = text
        }

        viewModel.init(itemId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.remove()
    }
}