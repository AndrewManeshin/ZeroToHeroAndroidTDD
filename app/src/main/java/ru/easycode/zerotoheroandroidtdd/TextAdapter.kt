package ru.easycode.zerotoheroandroidtdd

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ItemTextBinding

class TextAdapter : RecyclerView.Adapter<TextAdapter.TextViewHolder>() {

    private val list = ArrayList<CharSequence>()

    fun update(newList: List<CharSequence>) {
        val diffUtil = DiffUtilCallback(list, newList)
        val diff = DiffUtil.calculateDiff(diffUtil)
        list.clear()
        list.addAll(newList)
        diff.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        return TextViewHolder(ItemTextBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    class TextViewHolder(private val binding: ItemTextBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(source: CharSequence) {
            binding.root.text = source
        }
    }
}

private class DiffUtilCallback(
    private val old: List<CharSequence>,
    private val new: List<CharSequence>
) : DiffUtil.Callback() {

    override fun getOldListSize() = old.size

    override fun getNewListSize() = new.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        old[oldItemPosition] == new[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        old[oldItemPosition] == new[newItemPosition]
}