package me.mazzampr.infojajanan2.db

import androidx.lifecycle.LiveData
import androidx.room.*
import me.mazzampr.infojajanan2.pojo.Meal

@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(meal: Meal)

    @Delete
    suspend fun delete(meal: Meal)

    @Query("SELECT * FROM mealInformation")
    fun getAllMeal(): LiveData<List<Meal>>

    @Query("SELECT COUNT() FROM mealInformation WHERE idMeal = :id")
    suspend fun isExist(id: String): Int
}