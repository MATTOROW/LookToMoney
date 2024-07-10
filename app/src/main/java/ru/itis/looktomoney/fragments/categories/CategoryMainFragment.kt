package ru.itis.looktomoney.fragments.categories

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.itis.looktomoney.R
import ru.itis.looktomoney.adapters.AllCategorysAdapter
import ru.itis.looktomoney.databinding.FragmentCategoryMainBinding
import ru.itis.looktomoney.domain.DB_category_Helper


class CategoryMainFragment : Fragment(R.layout.fragment_category_main) {
    private var binding : FragmentCategoryMainBinding? = null

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCategoryMainBinding.bind(view)

        binding?.run {
            val db = DB_category_Helper(requireContext(), null)

            rvAllCategorys.layoutManager = LinearLayoutManager(requireContext())

            tvChoiceInput.setOnClickListener {
                tvChoiceInput.setTextColor(resources.getColor(R.color.medium_blue))
                tvChoiceOutput.setTextColor(resources.getColor(R.color.light_gray))
                rvAllCategorys.adapter = AllCategorysAdapter(db.getAllIncomeCategorys(), requireContext())
            }

            tvChoiceInput.callOnClick()

            tvChoiceOutput.setOnClickListener {
                tvChoiceInput.setTextColor(resources.getColor(R.color.light_gray))
                tvChoiceOutput.setTextColor(resources.getColor(R.color.medium_blue))
                rvAllCategorys.adapter = AllCategorysAdapter(db.getAllOutcomeCategorys(), requireContext())
            }

            btnAddCategory.setOnClickListener {
                findNavController().navigate(R.id.action_categoryMainFragment_to_addCategoryFragment)
            }
        }
    }
}