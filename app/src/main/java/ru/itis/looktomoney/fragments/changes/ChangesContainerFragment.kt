package ru.itis.looktomoney.fragments.changes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.itis.looktomoney.R
import ru.itis.looktomoney.databinding.FragmentCategoriesContainerBinding
import ru.itis.looktomoney.databinding.FragmentChangesContainerBinding

class ChangesContainerFragment : Fragment(R.layout.fragment_changes_container) {

    private var binding: FragmentChangesContainerBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChangesContainerBinding.bind(view)
    }

}