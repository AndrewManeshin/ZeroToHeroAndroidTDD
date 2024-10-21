package ru.easycode.zerotoheroandroidtdd.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.LayoutItemBinding
import ru.easycode.zerotoheroandroidtdd.presentation.DeleteItemUi
import ru.easycode.zerotoheroandroidtdd.presentation.ItemUi

class Adapter(
    private val itemClickListener: DeleteItemUi
) : RecyclerView.Adapter<Adapter.ItemViewHolder>() {

    private val list = ArrayList<ItemUi>()

    fun update(newList: List<ItemUi>) {
        val diffUtil = DiffUtilCallback(list, newList)
        val diff = DiffUtil.calculateDiff(diffUtil)
        list.clear()
        list.addAll(newList)
        diff.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutItemBinding.inflate(LayoutInflater.from(parent.context)),
            itemClickListener
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    class ItemViewHolder(
        private val binding: LayoutItemBinding,
        private val itemClickListener: DeleteItemUi
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(source: ItemUi) {
            source.show(binding.root)
            binding.root.setOnClickListener {
                source.delete(itemClickListener)
            }
        }
    }
}

private class DiffUtilCallback(
    private val old: List<ItemUi>,
    private val new: List<ItemUi>
) : DiffUtil.Callback() {

    override fun getOldListSize() = old.size

    override fun getNewListSize() = new.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        new[newItemPosition].areItemsSame(old[oldItemPosition])

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        old[oldItemPosition] == new[newItemPosition]
}