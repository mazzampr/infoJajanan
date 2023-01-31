package me.mazzampr.infojajanan2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.mazzampr.infojajanan2.databinding.MealsItemBinding
import me.mazzampr.infojajanan2.pojo.MealsByCategory

class CategoriesMealsDetailAdapter: RecyclerView.Adapter<CategoriesMealsDetailAdapter.CategoriesMealsViewHolder>() {

    private var categoriesMealsList = ArrayList<MealsByCategory>()

    fun setCategoryMeals(categoriesMealsList: List<MealsByCategory>) {
        this.categoriesMealsList.clear()
        this.categoriesMealsList.addAll(categoriesMealsList)
        this.categoriesMealsList = categoriesMealsList as ArrayList<MealsByCategory>
        notifyDataSetChanged()
    }

    inner class CategoriesMealsViewHolder(val binding: MealsItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesMealsViewHolder {
        return CategoriesMealsViewHolder(
            MealsItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: CategoriesMealsViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(categoriesMealsList[position].strMealThumb)
            .into(holder.binding.imgCategoryDetail)
        holder.binding.tvCategoryNameDetail.text = categoriesMealsList[position].strMeal
    }

    override fun getItemCount(): Int {
        return categoriesMealsList.size
    }
}