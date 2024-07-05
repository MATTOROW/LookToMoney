package ru.itis.looktomoney

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import ru.itis.looktomoney.adapters.IconAdapter
import ru.itis.looktomoney.adapters.Icons
import ru.itis.looktomoney.databinding.FragmentIconChoiceBinding


class IconChoiceFragment : Fragment(R.layout.fragment_icon_choice) {

    private var binding : FragmentIconChoiceBinding? = null

    private var adapter : IconAdapter? = null

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentIconChoiceBinding.bind(view)
        adapter = IconAdapter(Icons.list)

        binding?.run {
            iconRecycleView.adapter = adapter
            iconRecycleView.layoutManager = GridLayoutManager(requireContext(), 4)
        }
    }
}