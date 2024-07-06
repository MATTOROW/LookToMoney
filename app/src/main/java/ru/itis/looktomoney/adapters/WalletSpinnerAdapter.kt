package ru.itis.looktomoney.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import ru.itis.looktomoney.R
import ru.itis.looktomoney.domain.Category
import ru.itis.looktomoney.domain.Wallet

class WalletSpinnerAdapter(
    private val context: Context,
    private val list: ArrayList<Wallet>
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

        var view : View = layout.inflate(R.layout.item_wallet,p2, false)

        val wallet = getItem(p0) as Wallet

        view.findViewById<TextView>(R.id.wall_name).text = wallet.name.toString()
        view.findViewById<TextView>(R.id.wall_sum).text = wallet.numb.toString()
        view.findViewById<ImageView>(R.id.wall_icon).setImageResource(wallet.icon)

        return view
    }
}