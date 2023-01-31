package me.mazzampr.infojajanan2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.mazzampr.infojajanan2.databinding.CategoryItemBinding
import me.mazzampr.infojajanan2.pojo.CategoriesItem

class CategoriesAdapter: RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {
    lateinit var onItemClicked: ((CategoriesItem) -> Unit)
    private var categoriesList = ArrayList<CategoriesItem>()

    fun setCategoriesItem(categoriesList: List<CategoriesItem>) {
        this.categoriesList = categoriesList as ArrayList<CategoriesItem>
        notifyDataSetChanged()
    }

    inner class CategoriesViewHolder(val binding: CategoryItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder(
            CategoryItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        Glide.with(holder.itemView).load(categoriesList[position].strCategoryThumb).into(holder.binding.imgCategory)
        holder.binding.tvCategoryName.text = categoriesList[position].strCategory

        holder.itemView.setOnClickListener {
            onItemClicked.invoke(categoriesList[position])
        }
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }
}