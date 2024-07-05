package ru.itis.looktomoney.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import ru.itis.looktomoney.R
import ru.itis.looktomoney.domain.Wallet

class IconSpinnerAdapter(
    val context: Context,

) : BaseAdapter() {



    private val layout_inflater : LayoutInflater = context.getSystemService((Context.LAYOUT_INFLATER_SERVICE)) as LayoutInflater

    override fun getCount(): Int {
        return Icons.list.size
    }

    override fun getItem(p0: Int): Any {
        return Icons.list[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val num = Icons.list[p0]
        var view = layout_inflater.inflate(R.layout.item_icon_for_category, p2)

        view.findViewById<TextView>(R.id.icon_number).text = num.toString()
        view.findViewById<ImageView>(R.id.icon_image).setImageResource(num)

        return view
    }

}