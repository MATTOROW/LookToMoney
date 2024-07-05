package ru.itis.looktomoney.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import ru.itis.looktomoney.R

class IconSpinnerAdapter(
    private val context: Context,
    private val list: List<Int>
) : BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(p0: Int): Any {
        return list[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val layout = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        var view : View = layout.inflate(R.layout.item_icon_for_category,p2, false)

        val int = getItem(p0) as Int

        view.findViewById<ImageView>(R.id.icon_image).setImageResource(int)

        return view
    }
}