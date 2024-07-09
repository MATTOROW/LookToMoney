package ru.itis.looktomoney.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.itis.looktomoney.databinding.ItemForAllCategorysBinding
import ru.itis.looktomoney.databinding.ItemParentChangeBinding
import ru.itis.looktomoney.domain.Category
import ru.itis.looktomoney.domain.Day
import ru.itis.looktomoney.holders.AllCategorysHolder
import ru.itis.looktomoney.holders.ParentHolder

class AllCategorysAdapter(
    var list : ArrayList<Category>,
    val context : Context
) : RecyclerView.Adapter<AllCategorysHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllCategorysHolder {
        return AllCategorysHolder(
            ItemForAllCategorysBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), context
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AllCategorysHolder, position: Int) {
        holder.onBind(list[position])
    }

    fun updateDataset(newList: ArrayList<Category>) {
        list = newList
    }
}