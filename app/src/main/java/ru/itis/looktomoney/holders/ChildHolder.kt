package ru.itis.looktomoney.holders

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.icu.text.DecimalFormat
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.itis.looktomoney.adapters.AccountAdapter
import ru.itis.looktomoney.databinding.ChildChangeItemBinding
import ru.itis.looktomoney.databinding.ItemAccountBinding
import ru.itis.looktomoney.domain.Change
import ru.itis.looktomoney.domain.DB_days_Helper
import ru.itis.looktomoney.domain.DB_wallet_Helper
import ru.itis.looktomoney.domain.Wallet

class ChildHolder(
    private val binding : ChildChangeItemBinding,
) : ViewHolder(binding.root) {

    fun onBind(change : Change){
        binding.run {
            ivCatChildIcon.setImageResource(change.category!!.icon)
            tvChildWalletName.text = change.wallet!!.name
            tvChildCatName.text = change.category!!.name
            tvCatChilsSum.text = change.numb.toString()
            if (change.category!!.type.equals("Income")) tvCatChilsSum.setTextColor(Color.GREEN)
            else{
                tvCatChilsSum.setTextColor(Color.RED)
            }
        }
    }
}