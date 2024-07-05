package ru.itis.looktomoney.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.itis.looktomoney.databinding.ItemIconForCategoryBinding
import ru.itis.looktomoney.holders.IconHolder

class IconAdapter(
    private val list: List<Int>
) : RecyclerView.Adapter<IconHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IconHolder {
        return IconHolder(
            ItemIconForCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: IconHolder, position: Int) {
        holder.onBind(list[position])
    }
}