package ru.itis.looktomoney.holders

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.itis.looktomoney.adapters.AccountAdapter
import ru.itis.looktomoney.adapters.AllCategorysAdapter
import ru.itis.looktomoney.databinding.ItemChildChangeBinding
import ru.itis.looktomoney.databinding.ItemForAllCategorysBinding
import ru.itis.looktomoney.domain.Category
import ru.itis.looktomoney.domain.Change
import ru.itis.looktomoney.domain.DB_category_Helper
import ru.itis.looktomoney.domain.DB_days_Helper
import ru.itis.looktomoney.domain.DB_wallet_Helper
import ru.itis.looktomoney.domain.Wallet

class AllCategorysHolder(
    private val binding : ItemForAllCategorysBinding,
    private val context : Context,
    private val adapter: AllCategorysAdapter
) : ViewHolder(binding.root) {

    fun onBind(category : Category){
        binding.run {
            ivAllCatImage.setImageResource(category.icon)
            ivAllCatName.text = category.name.toString()

            // удаление
            ivDeleteCategory.setOnClickListener {
                val yesSelected: DialogInterface.OnClickListener =
                    object : DialogInterface.OnClickListener {
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            val db = DB_category_Helper(context, null)
                            db.deleteThisCategory(category)
                            adapter.notifyItemRemoved(adapterPosition)
                            when (category.type) {
                                "Income" -> adapter.updateDataset(db.getAllIncomeCategorys())
                                "Outcome" -> adapter.updateDataset(db.getAllOutcomeCategorys())
                            }
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