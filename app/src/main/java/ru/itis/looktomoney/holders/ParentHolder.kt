package ru.itis.looktomoney.holders

import android.graphics.Color
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.itis.looktomoney.adapters.ChildAdapter
import ru.itis.looktomoney.databinding.ItemParentChangeBinding
import ru.itis.looktomoney.domain.Change
import ru.itis.looktomoney.domain.Day

class ParentHolder(
    private val binding : ItemParentChangeBinding,
) : ViewHolder(binding.root) {

    fun onBind(day : Day){
        binding.run {
            parentRv.adapter = ChildAdapter(day.changes)
            parentRv.layoutManager = LinearLayoutManager(binding.root.context)

            tvParentDate.text = day.data.toString()

            var numb : Double = 0.0
            for (chr in day.changes){
                if (chr.category!!.type.equals("Income")) numb += chr.numb
                else{
                    numb -= chr.numb
                }
            }
            tvParentSum.text = numb.toString()
        }
    }
}