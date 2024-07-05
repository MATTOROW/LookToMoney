package ru.itis.looktomoney.fragments.accounts

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.itis.looktomoney.R
import ru.itis.looktomoney.databinding.FragmentAccountsContainerBinding

class AccountsContainerFragment : Fragment(R.layout.fragment_accounts_container) {
    private var binding: FragmentAccountsContainerBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAccountsContainerBinding.bind(view)
    }
}