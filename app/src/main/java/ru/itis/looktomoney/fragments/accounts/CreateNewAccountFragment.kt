package ru.itis.looktomoney.fragments.accounts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import ru.itis.looktomoney.R
import ru.itis.looktomoney.adapters.IconSpinnerAdapter
import ru.itis.looktomoney.adapters.Icons_Wallet
import ru.itis.looktomoney.databinding.FragmentCreateNewAccountBinding
import ru.itis.looktomoney.domain.DB_wallet_Helper
import ru.itis.looktomoney.domain.Wallet

class CreateNewAccountFragment : Fragment(R.layout.fragment_create_new_account) {

    private var binding: FragmentCreateNewAccountBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCreateNewAccountBinding.bind(view)

        val db_wallet = DB_wallet_Helper(requireContext(), null)

        binding?.run {

            var polz_icon = -1

            spinnerIconWallet.adapter = IconSpinnerAdapter(requireContext(), Icons_Wallet.list)

            val itemIconSelected: AdapterView.OnItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {

                        val item = parent.getItemAtPosition(position) as Int
                        polz_icon = item
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }
                }

            spinnerIconWallet.onItemSelectedListener = itemIconSelected

            btnAccConfirm.setOnClickListener {
                var text = inputAccName.text.toString()
                var numb : Double= -1.0
                try{
                    numb = inputAccSum.text.toString().toDouble()
                } catch (_ : Exception){}

                if (text == "" || numb == -1.0 || polz_icon == -1){
                    Toast.makeText(requireContext(), "Ошибка", Toast.LENGTH_SHORT).show()
                }
                else{
                    db_wallet.addWallet(Wallet(numb, text, polz_icon))
                    Toast.makeText(requireContext(), "Успешно", Toast.LENGTH_SHORT).show()
                    btnGoBack.callOnClick()

                }
            }

            btnGoBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}