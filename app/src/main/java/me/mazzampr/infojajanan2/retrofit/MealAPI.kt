package me.mazzampr.infojajanan2.retrofit

import me.mazzampr.infojajanan2.pojo.MealList
import retrofit2.Call
import retrofit2.http.GET

interface MealAPI {

    @GET("random.php")
    fun getRandomMeal():Call<MealList>

}