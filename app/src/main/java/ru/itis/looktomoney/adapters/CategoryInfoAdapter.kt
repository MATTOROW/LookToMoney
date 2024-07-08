package ru.itis.looktomoney.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.itis.looktomoney.databinding.ItemCategoryInfoBinding
import ru.itis.looktomoney.domain.Category
import ru.itis.looktomoney.domain.DB_days_Helper
import ru.itis.looktomoney.domain.Info
import ru.itis.looktomoney.holders.CategoryInfoHolder

class CategoryInfoAdapter(
    private val list: List<Info>
): RecyclerView.Adapter<CategoryInfoHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryInfoHolder {
        return CategoryInfoHolder(ItemCategoryInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CategoryInfoHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}
