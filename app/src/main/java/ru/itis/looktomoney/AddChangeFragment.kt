package ru.itis.looktomoney

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import ru.itis.looktomoney.databinding.FragmentAddChangeBinding
import ru.itis.looktomoney.domain.Change
import ru.itis.looktomoney.domain.DB_days_Helper
import ru.itis.looktomoney.domain.MyDate


class AddChangeFragment : Fragment(R.layout.fragment_add_change) {

    private var binding : FragmentAddChangeBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAddChangeBinding.bind(view)

        val db = context?.let { DB_days_Helper(it,null) } // Тут вполне может быть ошибка
        var polz_choice : Int = -1

        binding?.run {

            choiceInput.setOnClickListener{
                polz_choice = 0
                choiceInput.setTextColor(Color.GREEN)
                choiceOutput.setTextColor(Color.RED)
            }

            choiceOutput.setOnClickListener{
                polz_choice = 1
                choiceInput.setTextColor(Color.RED)
                choiceOutput.setTextColor(Color.GREEN)
            }



            addToDataBaseButton.setOnClickListener {
                var numb = polzNumber.text.toString().toInt()
                // нужно сделать проверку на корректный ввод даты
                var date = MyDate(polzDate.text.toString())

                if (db != null) {
                    //db.addChange(change = Change(int = numb, category = , wallet = ), date = date)
                    Toast.makeText(this.root.context, "Успешно", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}