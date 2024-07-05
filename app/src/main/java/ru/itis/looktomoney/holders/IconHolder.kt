package ru.itis.looktomoney.holders

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.itis.looktomoney.databinding.ItemCategoryBinding
import ru.itis.looktomoney.databinding.ItemIconForCategoryBinding

class IconHolder(
    private val binding : ItemIconForCategoryBinding
) : ViewHolder(binding.root) {

    fun onBind(int : Int){
        binding.iconImage.setImageResource(int)
    }
}