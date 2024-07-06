package ru.itis.looktomoney.holders

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.itis.looktomoney.databinding.ItemAccountBinding
import ru.itis.looktomoney.domain.Wallet

class AccountHolder(
    private val binding : ItemAccountBinding
) : ViewHolder(binding.root) {

    fun onBind(wallet : Wallet){
        binding?.run {
            ivAccIcon.setImageResource(wallet.icon)
            tvAccName.text = wallet.name
            tvAccBalance.text = wallet.numb.toString()
        }
    }
}