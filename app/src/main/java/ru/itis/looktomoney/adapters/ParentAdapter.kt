package ru.itis.looktomoney.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.itis.looktomoney.databinding.ChildChangeItemBinding
import ru.itis.looktomoney.databinding.ParentChangeItemBinding
import ru.itis.looktomoney.domain.Change
import ru.itis.looktomoney.domain.Day
import ru.itis.looktomoney.holders.ChildHolder
import ru.itis.looktomoney.holders.ParentHolder

class ParentAdapter(
    var list : ArrayList<Day>,
) : RecyclerView.Adapter<ParentHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentHolder {
        return ParentHolder(
            ParentChangeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ParentHolder, position: Int) {
        holder.onBind(list[position])
    }

    fun updateDataset(newList: ArrayList<Day>) {
        list = newList
    }
}