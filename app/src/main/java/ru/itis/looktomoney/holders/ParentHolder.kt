package ru.itis.looktomoney.holders

import android.content.Context
import android.graphics.Color
import android.icu.text.DecimalFormat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.itis.looktomoney.adapters.ChildAdapter
import ru.itis.looktomoney.adapters.ParentAdapter
import ru.itis.looktomoney.databinding.ItemParentChangeBinding
import ru.itis.looktomoney.domain.Change
import ru.itis.looktomoney.domain.Day

class ParentHolder(
    private val binding : ItemParentChangeBinding,
    val context: Context,
    val adapter: ParentAdapter
) : ViewHolder(binding.root) {

    fun onBind(day : Day){
        binding.run {
            parentRv.adapter = ChildAdapter(day.changes, context, day.data, this@ParentHolder)
            parentRv.layoutManager = LinearLayoutManager(binding.root.context)

            tvParentDate.text = day.data.toString()

            var numb : Double = 0.0
            for (chr in day.changes){
                if (chr.category!!.type.equals("Income")) numb += chr.numb
                else{
                    numb -= chr.numb
                }
            }

            if (numb > 0){
                tvParentSum.text = DecimalFormat("#0.00").format(numb) + " ₽"
                tvParentSum.setTextColor(Color.GREEN)
            }
            if (numb < 0){
                tvParentSum.text = DecimalFormat("#0.00").format((numb * -1)) + " ₽"
                tvParentSum.setTextColor(Color.RED)
            }
        }
    }

    fun removeDate() {
        adapter.notifyItemRemoved(adapterPosition)
        adapter.list.removeAt(adapterPosition + 1)
    }
}