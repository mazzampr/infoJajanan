package me.mazzampr.infojajanan2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.mazzampr.infojajanan2.databinding.ItemPopularMealBinding
import me.mazzampr.infojajanan2.pojo.CategoryMealsItem

class PopularMealAdapter(): RecyclerView.Adapter<PopularMealAdapter.MealPopularViewHolder>() {
    lateinit var onItemClick: ((CategoryMealsItem) -> Unit)
    private var mealsList = ArrayList<CategoryMealsItem>()

    fun setMeals(mealsList: ArrayList<CategoryMealsItem>) {
        this.mealsList = mealsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealPopularViewHolder {
        return MealPopularViewHolder(ItemPopularMealBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MealPopularViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(mealsList[position].strMealThumb)
            .into(holder.binding.imgPopularMealItem)

        holder.itemView.setOnClickListener {
            onItemClick.invoke(mealsList[position])
        }
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }

    class MealPopularViewHolder(val binding: ItemPopularMealBinding): RecyclerView.ViewHolder(binding.root)


}