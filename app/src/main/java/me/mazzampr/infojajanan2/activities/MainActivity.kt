package me.mazzampr.infojajanan2.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import me.mazzampr.infojajanan2.R
import me.mazzampr.infojajanan2.db.MealDatabase
import me.mazzampr.infojajanan2.viewModel.HomeViewModel
import me.mazzampr.infojajanan2.viewModel.HomeViewModelFactory

class MainActivity : AppCompatActivity() {
    val viewModel: HomeViewModel by lazy {
        val mealDatabase = MealDatabase.getInstance(this)
        val viewModelFactory = HomeViewModelFactory(mealDatabase)
        ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.btm_nav)
        val navController = findNavController(R.id.host_fragment)
        bottomNavigationView.setupWithNavController(navController)
    }
}