package ru.itis.looktomoney.fragments.accounts

import android.icu.text.DecimalFormat
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.itis.looktomoney.R
import ru.itis.looktomoney.adapters.AccountAdapter
import ru.itis.looktomoney.databinding.FragmentAccountMainBinding
import ru.itis.looktomoney.domain.DB_wallet_Helper

class AccountMainFragment : Fragment(R.layout.fragment_account_main) {
    private var binding: FragmentAccountMainBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAccountMainBinding.bind(view)

        val dbWalletHelper = DB_wallet_Helper(requireContext(), null)

        binding?.run {
            tvAllMoney.text = DecimalFormat("#0.00").format(dbWalletHelper.getAllSum()) + " ₽"
            rvAccContainer.adapter = AccountAdapter(dbWalletHelper.getAll(), requireContext(), this@AccountMainFragment)
            rvAccContainer.layoutManager = LinearLayoutManager(requireContext())

            btnAddAcc.setOnClickListener {
                findNavController().navigate(R.id.action_accountMainFragment_to_createNewAccountFragment)
            }
        }
    }

    fun updateAllSum() {
        val dbWalletHelper = DB_wallet_Helper(requireContext(), null)
        binding?.tvAllMoney?.text = DecimalFormat("#0.00").format(dbWalletHelper.getAllSum()) + " ₽"
    }
}