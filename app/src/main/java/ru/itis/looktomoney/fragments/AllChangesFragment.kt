package ru.itis.looktomoney.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.itis.looktomoney.R
import ru.itis.looktomoney.adapters.ParentAdapter
import ru.itis.looktomoney.databinding.FragmentAllChangesBinding
import ru.itis.looktomoney.domain.DB_days_Helper


class AllChangesFragment : Fragment(R.layout.fragment_all_changes) {

    private var binding : FragmentAllChangesBinding? = null

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAllChangesBinding.bind(view)

        binding?.run {
            val db = DB_days_Helper(requireContext(), null)
            rvAllChanges.adapter = ParentAdapter(db.getAll())
            rvAllChanges.layoutManager = LinearLayoutManager(requireContext())
        }
    }
}