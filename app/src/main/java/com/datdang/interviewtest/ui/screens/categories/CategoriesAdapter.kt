package com.datdang.interviewtest.ui.screens.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.datdang.domain.model.category.Category
import com.datdang.interviewtest.R
import com.datdang.interviewtest.databinding.ItemCategoryBinding
import com.datdang.interviewtest.utils.ItemClickable
import com.datdang.interviewtest.utils.ItemClickableImpl
import kotlinx.android.extensions.LayoutContainer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class CategoriesAdapter
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(), ItemClickable<CategoriesAdapter.OnItemClick> by ItemClickableImpl() {

    var items = mutableListOf<Category?>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        return ViewHolderCategories(ItemCategoryBinding.inflate(inflate, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolderCategories -> holder.bind(items[position])
        }
    }

    internal inner class ViewHolderCategories(
            private val binding: ItemCategoryBinding,
    ) : RecyclerView.ViewHolder(binding.root), LayoutContainer {
        override val containerView: View get() = itemView

        init {
            itemView.setOnClickListener {
                val position = bindingAdapterPosition
                items[position]?.let { data ->
                    CoroutineScope(Dispatchers.IO).launch {
                        notifyItemClick(OnItemClick.ItemCategory(data))
                    }
                    data.isSelected = !data.isSelected
                    notifyItemChanged(position)
                }
            }
        }

        fun bind(model: Category?) {
            binding.tvCategoryName.text = model?.name
            binding.root.setBackgroundResource(if (model?.isSelected == true) R.drawable.bg_item_category_selected else R.drawable.bg_item_category_unselected)
        }
    }

    sealed class OnItemClick {
        data class ItemCategory(val data: Category) : OnItemClick()
    }

}