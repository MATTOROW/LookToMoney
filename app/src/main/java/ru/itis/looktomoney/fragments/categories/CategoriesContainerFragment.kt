package ru.itis.looktomoney.fragments.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.itis.looktomoney.R
import ru.itis.looktomoney.databinding.FragmentCategoriesContainerBinding

class CategoriesContainerFragment : Fragment(R.layout.fragment_categories_container) {

    private var binding: FragmentCategoriesContainerBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCategoriesContainerBinding.bind(view)
    }
}