package ru.itis.looktomoney

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import ru.itis.looktomoney.adapters.CategorySpinnerAdapter
import ru.itis.looktomoney.adapters.IconSpinnerAdapter
import ru.itis.looktomoney.databinding.FragmentAddCategoryBinding
import ru.itis.looktomoney.domain.Category
import ru.itis.looktomoney.domain.DB_category_Helper

class AddCategoryFragment : Fragment(R.layout.fragment_add_category) {

    private var binding : FragmentAddCategoryBinding? = null

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddCategoryBinding.bind(view)

        binding?.run {
            var polz_choice = -1

            var polz_icon = R.drawable.baseline_attach_money_24

            var db = DB_category_Helper(this.root.context, null)
            choiceInputInCat.setOnClickListener{
                polz_choice = 0
                choiceInputInCat.setTextColor(Color.GREEN)
                choiceOutputInCat.setTextColor(Color.RED)
            }

            iconSpinner.adapter = IconSpinnerAdapter(requireContext())

            val itemSelected: AdapterView.OnItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View,
                        position: Int,
                        id: Long
                    ) {
                        val item : Int = parent.getItemAtPosition(position) as Int
                        polz_icon = item
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }
                }
            iconSpinner.onItemSelectedListener = itemSelected

            choiceOutputInCat.setOnClickListener{
                polz_choice = 1
                choiceInputInCat.setTextColor(Color.RED)
                choiceOutputInCat.setTextColor(Color.GREEN)
            }

            buttonAppend.setOnClickListener {
                var text = polzName.text.toString()

                var desk = polzDesk.text.toString()


                if (text == "" || polz_choice == -1){
                    Toast.makeText(this.root.context, "Ошибка", Toast.LENGTH_SHORT).show()
                }
                else{
                    var type : String = ""
                    if (polz_choice == 0) type = "Income"
                    else{
                        type = "Outcome"
                    }
                    db.addCategory(Category(text,
                        desk,
                        polz_icon,
                        type
                        ))

                    Toast.makeText(this.root.context, "Успешно", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}