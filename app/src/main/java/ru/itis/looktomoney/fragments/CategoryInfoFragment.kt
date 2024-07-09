package ru.itis.looktomoney.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.itis.looktomoney.R
import ru.itis.looktomoney.adapters.CategoryInfoAdapter
import ru.itis.looktomoney.databinding.FragmentCategoryInfoBinding
import ru.itis.looktomoney.domain.Category
import ru.itis.looktomoney.domain.Change
import ru.itis.looktomoney.domain.DB_category_Helper
import ru.itis.looktomoney.domain.DB_days_Helper
import ru.itis.looktomoney.domain.Info
import kotlin.math.roundToInt

class CategoryInfoFragment : Fragment(R.layout.fragment_category_info) {
    private var binding: FragmentCategoryInfoBinding? = null
    private var adapter: CategoryInfoAdapter? = null
    private var dbCategory: DB_category_Helper? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCategoryInfoBinding.bind(view)
        dbCategory = context?.let { it1 -> DB_category_Helper(it1, null) }
        val categoryListIn = dbCategory?.let {
            CategoryInfoAdapter(
                createInfoList(
                    it.getAllIncomeCategorys(),
                    true
                )
            )
        }
        val categoryListOut = dbCategory?.let {
            CategoryInfoAdapter(
                createInfoList(
                    it.getAllOutcomeCategorys(),
                    false
                )
            )
        }
        adapter = dbCategory?.let { categoryListIn }
        binding?.run {
            rvInfo.adapter = adapter
            rvInfo.layoutManager = LinearLayoutManager(requireContext())
            choiceIncome.setTextColor(Color.GREEN)
            choiceExpenses.setTextColor(Color.RED)
            choiceExpenses.setOnClickListener {
                choiceIncome.setTextColor(Color.RED)
                choiceExpenses.setTextColor(Color.GREEN)
                adapter = dbCategory?.let { categoryListOut }
                rvInfo.adapter = adapter
                rvInfo.layoutManager = LinearLayoutManager(requireContext())
            }
            choiceIncome.setOnClickListener {
                choiceIncome.setTextColor(Color.GREEN)
                choiceExpenses.setTextColor(Color.RED)
                adapter = dbCategory?.let { categoryListIn }
                rvInfo.adapter = adapter
                rvInfo.layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun createInfoList(categoryList: List<Category>, income: Boolean): List<Info> {
        val infoList = ArrayList<Info>()
        val db = context?.let { DB_days_Helper(it, null) }
        var sum = 0.0
        val mapOfCategory = db?.getAll()?.map { day ->
            day.changes
        }?.reduceOrNull { acc, changes ->
            (acc + changes) as ArrayList<Change>
        }?.groupBy { it.category?.name ?: "no category" }

        categoryList.forEach { category ->
            var change = 0.0
            mapOfCategory?.get(category.name)?.forEach {
                change += it.numb
                sum += it.numb
            }
            infoList.add(Info(category, change.toString()))
        }
        infoList.forEach {
            val ch: Double = it.info.toDouble()
            if (sum == 0.0) {
                if (income) it.info = "нет доходов"
                else it.info = "нет расходов"
            } else {
                it.info += " руб / ${(ch / sum * 100).roundToInt()} %"
            }
        }
        return infoList

    }
}