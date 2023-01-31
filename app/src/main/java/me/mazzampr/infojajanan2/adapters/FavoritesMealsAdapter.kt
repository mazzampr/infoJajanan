package me.mazzampr.infojajanan2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.mazzampr.infojajanan2.databinding.MealsItemBinding
import me.mazzampr.infojajanan2.pojo.Meal

class FavoritesMealsAdapter: RecyclerView.Adapter<FavoritesMealsAdapter.FavoriteMealsViewHolder>() {
    lateinit var onItemClicked: ((Meal) -> Unit)
    inner class FavoriteMealsViewHolder(val binding: MealsItemBinding): RecyclerView.ViewHolder(binding.root)

    private val diffUtil = object : DiffUtil.ItemCallback<Meal>() {
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem.idMeal == newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMealsViewHolder {
        return FavoriteMealsViewHolder(
            MealsItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteMealsViewHolder, position: Int) {
        val meal = differ.currentList[position]
        Glide.with(holder.itemView).load(meal.strMealThumb).into(holder.binding.imgCategoryDetail)
        holder.binding.tvCategoryNameDetail.text = meal.strMeal
        holder.itemView.setOnClickListener {
            onItemClicked.invoke(differ.currentList[position])
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}