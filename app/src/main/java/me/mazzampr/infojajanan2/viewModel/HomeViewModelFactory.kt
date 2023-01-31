package me.mazzampr.infojajanan2.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.mazzampr.infojajanan2.db.MealDatabase


class HomeViewModelFactory(
    val mealDatabase: MealDatabase
): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(mealDatabase) as T
    }
}