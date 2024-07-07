package ru.itis.looktomoney.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.itis.looktomoney.databinding.ItemAccountBinding
import ru.itis.looktomoney.domain.Wallet
import ru.itis.looktomoney.holders.AccountHolder
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.Private


class AccountAdapter(
    var list : ArrayList<Wallet>,
    private val context : Context
) : RecyclerView.Adapter<AccountHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountHolder {
        return AccountHolder(
            ItemAccountBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            context,
            this
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AccountHolder, position: Int) {
        holder.onBind(list[position])
    }

    fun updateDataset(newList: ArrayList<Wallet>) {
        list = newList
    }
}