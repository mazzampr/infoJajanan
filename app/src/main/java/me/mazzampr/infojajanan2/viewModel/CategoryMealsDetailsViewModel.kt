package me.mazzampr.infojajanan2.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.mazzampr.infojajanan2.pojo.MealsByCategory
import me.mazzampr.infojajanan2.pojo.MealsByCategoryList
import me.mazzampr.infojajanan2.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryMealsDetailsViewModel: ViewModel() {

    private var categoryMealsLiveData = MutableLiveData<List<MealsByCategory>>()

    fun getCategoryMealsList(categoryName: String) {
        RetrofitInstance.api.getMealsByCategory(categoryName).enqueue(object : Callback<MealsByCategoryList> {
            override fun onResponse(
                call: Call<MealsByCategoryList>,
                response: Response<MealsByCategoryList>,
            ) {
                if (response.body() != null) {
                    categoryMealsLiveData.value = response.body()!!.meals
                }
            }

            override fun onFailure(call: Call<MealsByCategoryList>, t: Throwable) {
                Log.d("Error at CategoryMeals", t.message.toString())
            }

        })
    }

    fun observeCategoryMealsLiveData(): LiveData<List<MealsByCategory>> {
        return categoryMealsLiveData
    }
}