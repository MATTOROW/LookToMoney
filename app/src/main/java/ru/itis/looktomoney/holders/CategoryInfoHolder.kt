package ru.itis.looktomoney.holders
import ru.itis.looktomoney.domain.Category
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kotlinx.coroutines.currentCoroutineContext
import ru.itis.looktomoney.databinding.ItemCategoryInfoBinding
import ru.itis.looktomoney.domain.DB_category_Helper
import ru.itis.looktomoney.domain.Info


class CategoryInfoHolder(
    private val binding: ItemCategoryInfoBinding
) : ViewHolder(binding.root){
    fun onBind(info: Info){
        binding.run{
            tvCategory.text = info.category.name
            tvMoney.text = info.info
            ivCatImage.setImageResource(info.category.icon)
        }
    }
}