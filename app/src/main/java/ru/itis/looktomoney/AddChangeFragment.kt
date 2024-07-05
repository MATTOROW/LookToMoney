package ru.itis.looktomoney

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
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


            catSpinner.adapter = CategorySpinnerAdapter(this.root.context, ArrayList())

            walletSpinner.adapter = WalletSpinnerAdapter(this.root.context, db_wallet.getAll())

            choiceInput.setOnClickListener{
                polz_choice_type = 0
                choiceInput.setTextColor(Color.GREEN)
                choiceOutput.setTextColor(Color.RED)
                catSpinner.adapter = CategorySpinnerAdapter(this.root.context, db_category.getAllIncomeCategorys())
            }

            choiceOutput.setOnClickListener{
                polz_choice_type = 1
                choiceInput.setTextColor(Color.RED)
                choiceOutput.setTextColor(Color.GREEN)
                catSpinner.adapter = CategorySpinnerAdapter(this.root.context, db_category.getAllOutcomeCategorys())
            }



            addToDataBaseButton.setOnClickListener {
                var cat : Category? = null
                var wallet : Wallet? = null

                val itemSelectedListener: AdapterView.OnItemSelectedListener =
                    object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>,
                            view: View,
                            position: Int,
                            id: Long
                        ) {
                            val item : Category = catSpinner.adapter.getItem(position) as Category
                            cat = item
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {
                        }
                    }

                catSpinner.onItemSelectedListener = itemSelectedListener

                val itemSelectedListener_wal: AdapterView.OnItemSelectedListener =
                    object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>,
                            view: View,
                            position: Int,
                            id: Long
                        ) {
                            val item : Wallet = walletSpinner.adapter.getItem(position) as Wallet
                            wallet = item
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {
                        }
                    }

                walletSpinner.onItemSelectedListener = itemSelectedListener_wal

                var numb = -1
                try {
                    numb = polzNumber.text.toString().toInt()
                } catch (_: Exception){}

                var date : MyDate? = null

                 try {
                     date = MyDate(polzDate.text.toString()) // нужно сделать, чтобы обрабатывалось, если пользователь не так ввёл дату
                 } catch (_ : Exception){}

                if (polz_choice_type == -1 || wallet == null || cat == null || numb == -1 || date == null){
                    Toast.makeText(this.root.context, "Ошибка", Toast.LENGTH_SHORT ).show()
                }
                else{
                    db_change.addChange(Change(int = numb, category = cat!!, wallet = wallet!!), date)
                    Toast.makeText(this.root.context, "Успешно", Toast.LENGTH_SHORT ).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}

