package ru.itis.looktomoney.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.itis.looktomoney.R
import ru.itis.looktomoney.databinding.FragmentCategoryInfoBinding

class CategoryInfoFragment : Fragment(R.layout.fragment_category_info) {
    private var binding: FragmentCategoryInfoBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCategoryInfoBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}