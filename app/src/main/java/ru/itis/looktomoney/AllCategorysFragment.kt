package ru.itis.looktomoney

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import ru.itis.looktomoney.adapters.AllCategorysAdapter
import ru.itis.looktomoney.databinding.FragmentAllCategorysBinding
import ru.itis.looktomoney.domain.DB_category_Helper


class AllCategorysFragment : Fragment(R.layout.fragment_all_categorys) {
    private var binding : FragmentAllCategorysBinding? = null

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAllCategorysBinding.bind(view)

        binding?.run {
            val db = DB_category_Helper(requireContext(), null)

            rvAllCategorys.layoutManager = LinearLayoutManager(requireContext())

            tvChoiceInput.setOnClickListener {
                tvChoiceInput.setTextColor(Color.GREEN)
                tvChoiceOutput.setTextColor(Color.RED)
                rvAllCategorys.adapter = AllCategorysAdapter(db.getAllIncomeCategorys(), requireContext())
            }

            tvChoiceOutput.setOnClickListener {
                tvChoiceInput.setTextColor(Color.RED)
                tvChoiceOutput.setTextColor(Color.GREEN)
                rvAllCategorys.adapter = AllCategorysAdapter(db.getAllOutcomeCategorys(), requireContext())
            }
        }
    }
}