package ru.itis.looktomoney.holders

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.itis.looktomoney.adapters.AccountAdapter
import ru.itis.looktomoney.databinding.ItemAccountBinding
import ru.itis.looktomoney.domain.DB_days_Helper
import ru.itis.looktomoney.domain.DB_wallet_Helper
import ru.itis.looktomoney.domain.Wallet

class AccountHolder(
    private val binding : ItemAccountBinding,
    private val fragmnet_context : Context,
    private val adapter: AccountAdapter
) : ViewHolder(binding.root) {

    fun onBind(wallet : Wallet){
        binding.run {
            ivAccIcon.setImageResource(wallet.icon)
            tvAccName.text = wallet.name
            tvAccBalance.text = wallet.numb.toString() + " ₽"


            ivDeleteAccount.setOnClickListener {

                val yesSelected: DialogInterface.OnClickListener =
                    object : DialogInterface.OnClickListener {
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            val db = DB_wallet_Helper(fragmnet_context, null)
                            val wl : Wallet? = db.getByName(wallet.name)

                            db.delteByName(wl!!.name)
                            val ind = adapter.list.indexOf(adapter.list.find{it.name.equals(wl.name)})
                            adapter.updateDataset(db.getAll())
                            adapter.notifyItemRemoved(ind)

                            val db_days = DB_days_Helper(fragmnet_context, null)
                            db_days.replaceAllWithoutThisWallet(wl)
                        }
                    }

                val noSelected: DialogInterface.OnClickListener =
                    object : DialogInterface.OnClickListener {
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            p0!!.cancel();
                        }
                    }

                var alertDialogBuilder : AlertDialog.Builder = AlertDialog.Builder(fragmnet_context)
                alertDialogBuilder.setTitle("Подтверждение")
                    .setMessage("Точно удалить?")
                    .setCancelable(true)
                    .setPositiveButton("Да", yesSelected)
                    .setNegativeButton("Нет", noSelected)

                val dialog = alertDialogBuilder.create()
                dialog.show()
            }
        }
    }
}