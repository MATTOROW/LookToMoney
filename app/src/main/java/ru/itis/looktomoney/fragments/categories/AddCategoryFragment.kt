package ru.itis.looktomoney.fragments.categories

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.itis.looktomoney.adapters.IconSpinnerAdapter
import ru.itis.looktomoney.adapters.Icons
import ru.itis.looktomoney.R
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

        var pref = requireActivity().getSharedPreferences("ICON", Context.MODE_PRIVATE)

        binding?.run {
            var polz_choice = -1

            var polz_icon = -1

            spinnerCatIcon.adapter = IconSpinnerAdapter(requireContext(), Icons.list)
            var db = DB_category_Helper(this.root.context, null)

            val itemSelectedListener: AdapterView.OnItemSelectedListener =
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
            spinnerCatIcon.onItemSelectedListener = itemSelectedListener

            choiceInputInCat.setOnClickListener{
                polz_choice = 0
                choiceInputInCat.setTextColor(resources.getColor(R.color.medium_blue))
                choiceOutputInCat.setTextColor(resources.getColor(R.color.light_gray))
            }

            choiceInputInCat.callOnClick()

            choiceOutputInCat.setOnClickListener{
                polz_choice = 1
                choiceInputInCat.setTextColor(resources.getColor(R.color.light_gray))
                choiceOutputInCat.setTextColor(resources.getColor(R.color.medium_blue))
            }


            btnCategoryConfirm.setOnClickListener {
                var text = etCatName.text.toString()

                var desk = etCatDesc.text.toString()


                if (text == "" || polz_choice == -1 || polz_icon == -1){
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
            btnGoBackCat.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

}