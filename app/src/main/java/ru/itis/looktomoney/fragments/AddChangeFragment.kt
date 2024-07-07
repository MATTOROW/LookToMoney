package ru.itis.looktomoney.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import ru.itis.looktomoney.DateValidator.DateValidatorUsingDateFormat
import ru.itis.looktomoney.R
import ru.itis.looktomoney.adapters.CategorySpinnerAdapter
import ru.itis.looktomoney.adapters.WalletSpinnerAdapter
import ru.itis.looktomoney.databinding.FragmentAddChangeBinding
import ru.itis.looktomoney.domain.Category
import ru.itis.looktomoney.domain.Change
import ru.itis.looktomoney.domain.DB_category_Helper
import ru.itis.looktomoney.domain.DB_days_Helper
import ru.itis.looktomoney.domain.DB_wallet_Helper
import ru.itis.looktomoney.domain.MyDate
import ru.itis.looktomoney.domain.Wallet

class AddChangeFragment : Fragment(R.layout.fragment_add_change){

    private var binding : FragmentAddChangeBinding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAddChangeBinding.bind(view)



        var polz_choice_type = -1

        binding?.run {
            val db_category = DB_category_Helper(this.root.context, null)
            val db_change = DB_days_Helper(this.root.context, null)
            val db_wallet = DB_wallet_Helper(this.root.context, null)

            spinnerWallet.adapter = WalletSpinnerAdapter(requireContext(), db_wallet.getAll())


            choiceInput.setOnClickListener{
                polz_choice_type = 0
                choiceInput.setTextColor(Color.GREEN)
                choiceOutput.setTextColor(Color.RED)
                spinnerCategory.adapter = CategorySpinnerAdapter(requireContext(), db_category.getAllIncomeCategorys())
            }

            choiceOutput.setOnClickListener{
                polz_choice_type = 1
                choiceInput.setTextColor(Color.RED)
                choiceOutput.setTextColor(Color.GREEN)
                spinnerCategory.adapter = CategorySpinnerAdapter(requireContext(), db_category.getAllOutcomeCategorys())
            }

            var cat : Category? = null
            var wallet : Wallet? = null

            val catSelected: AdapterView.OnItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {

                        val item = parent.getItemAtPosition(position) as Category // я не уверен в правильности этого
                        cat = item
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }
                }
            spinnerCategory.onItemSelectedListener = catSelected

            val walletSelected: AdapterView.OnItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {

                        val item = parent.getItemAtPosition(position) as Wallet // я не уверен в правильности этого
                        wallet = item
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }
                }
            spinnerWallet.onItemSelectedListener = walletSelected

            addToDataBaseButton.setOnClickListener {
                var numb = -1.0
                try {
                    numb = polzNumber.text.toString().toDouble()
                } catch (_: Exception){}

                var date : MyDate? = null

//                 try {
//                     date = MyDate(polzDate.text.toString()) // нужно сделать, чтобы обрабатывалось, если пользователь не так ввёл дату
//                 } catch (_ : Exception){}


                if (DateValidatorUsingDateFormat("dd.MM.yyyy").isValid(polzDate.text.toString())){
                    date = MyDate(polzDate.text.toString())
                }


                if (polz_choice_type == -1 || wallet == null || cat == null || numb == -1.0 || date == null){
                    Toast.makeText(this.root.context, "Ошибка", Toast.LENGTH_SHORT ).show()
                }
                else{
                    db_change.addChange(Change(int = numb, category = cat!!, wallet = wallet!!), date)
                    Toast.makeText(this.root.context, "Успешно", Toast.LENGTH_SHORT ).show()
                    spinnerWallet.adapter = WalletSpinnerAdapter(requireContext(), db_wallet.getAll())
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}

