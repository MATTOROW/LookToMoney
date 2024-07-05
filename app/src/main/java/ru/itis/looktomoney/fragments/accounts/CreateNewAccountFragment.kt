package ru.itis.looktomoney.fragments.accounts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import ru.itis.looktomoney.R
import ru.itis.looktomoney.databinding.FragmentCreateNewAccountBinding

class CreateNewAccountFragment : Fragment(R.layout.fragment_create_new_account) {

    private var binding: FragmentCreateNewAccountBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCreateNewAccountBinding.bind(view)
    }
}