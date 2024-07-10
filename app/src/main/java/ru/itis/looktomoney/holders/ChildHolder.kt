package ru.itis.looktomoney.holders

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.icu.text.DecimalFormat
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.itis.looktomoney.R
import ru.itis.looktomoney.adapters.AccountAdapter
import ru.itis.looktomoney.adapters.ChildAdapter
import ru.itis.looktomoney.databinding.ItemChildChangeBinding
import ru.itis.looktomoney.databinding.ItemAccountBinding
import ru.itis.looktomoney.domain.Change
import ru.itis.looktomoney.domain.DB_category_Helper
import ru.itis.looktomoney.domain.DB_days_Helper
import ru.itis.looktomoney.domain.DB_wallet_Helper
import ru.itis.looktomoney.domain.MyDate
import ru.itis.looktomoney.domain.Wallet

class ChildHolder(
    private val binding : ItemChildChangeBinding,
    val context: Context,
    val date: MyDate,
    val adapter: ChildAdapter,
    val parentHolder: ParentHolder
) : ViewHolder(binding.root) {
    fun onBind(change : Change){
        binding.run {
            ivCatChildIcon.setImageResource(change.category!!.icon)
            tvChildWalletName.text = change.wallet!!.name
            tvChildCatName.text = change.category!!.name
            if (change.category!!.type.equals("Income")) {
                tvCatChilsSum.text = "+" + DecimalFormat("#0.00").format(change.numb) + " ₽"
                tvCatChilsSum.setTextColor(binding.root.resources.getColor(R.color.green))
            } else {
                tvCatChilsSum.text = "-" + DecimalFormat("#0.00").format(change.numb) + " ₽"
                tvCatChilsSum.setTextColor(binding.root.resources.getColor(R.color.red))
            }

            ivDeleteChange.setOnClickListener {
                val yesSelected: DialogInterface.OnClickListener =
                    object : DialogInterface.OnClickListener {
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            val db = DB_days_Helper(context, null)
                            db.deleteChangeByDate(date, change)
                            if (adapter.list.size == 1) {
                                parentHolder.removeDate()
                            } else {
                                adapter.notifyItemRemoved(adapterPosition)
                                adapter.list.removeAt(adapterPosition + 1)
                            }
                            parentHolder.updateAllSum()
                        }
                    }

                val noSelected: DialogInterface.OnClickListener =
                    object : DialogInterface.OnClickListener {
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            p0!!.cancel();
                        }
                    }

                var alertDialogBuilder : AlertDialog.Builder = AlertDialog.Builder(context)
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