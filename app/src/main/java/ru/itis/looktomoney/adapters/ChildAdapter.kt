package ru.itis.looktomoney.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.itis.looktomoney.databinding.ItemAccountBinding
import ru.itis.looktomoney.databinding.ItemChildChangeBinding
import ru.itis.looktomoney.domain.Change
import ru.itis.looktomoney.domain.MyDate
import ru.itis.looktomoney.domain.Wallet
import ru.itis.looktomoney.holders.AccountHolder
import ru.itis.looktomoney.holders.ChildHolder
import ru.itis.looktomoney.holders.ParentHolder

class ChildAdapter(
    var list : ArrayList<Change>,
    val context : Context,
    val date : MyDate,
    val parentHolder: ParentHolder
) : RecyclerView.Adapter<ChildHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildHolder {
        return ChildHolder(
            ItemChildChangeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), context, date, this, parentHolder
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ChildHolder, position: Int) {
        holder.onBind(list[position])
    }

    fun updateDataset(newList: ArrayList<Change>) {
        list = newList
    }
}