package ru.itis.looktomoney.fragments.accounts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.itis.looktomoney.R
import ru.itis.looktomoney.databinding.FragmentAccountMainBinding

class AccountMainFragment : Fragment(R.layout.fragment_account_main) {
    private var binding: FragmentAccountMainBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAccountMainBinding.bind(view)
    }
}