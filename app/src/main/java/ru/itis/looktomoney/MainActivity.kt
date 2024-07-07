package ru.itis.looktomoney

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import ru.itis.looktomoney.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(R.color.medium_blue)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        controller =
            (supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment).navController

        binding.run {
            navbarBottom.setupWithNavController(controller)
        }
    }
}