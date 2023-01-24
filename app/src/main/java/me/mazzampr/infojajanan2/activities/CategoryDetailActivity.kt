package me.mazzampr.infojajanan2.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import me.mazzampr.infojajanan2.adapters.CategoriesMealsDetailAdapter
import me.mazzampr.infojajanan2.databinding.ActivityCategoryDetailBinding
import me.mazzampr.infojajanan2.fragments.HomeFragment
import me.mazzampr.infojajanan2.viewModel.CategoryMealsDetailsViewModel

class CategoryDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryDetailBinding
    private lateinit var categoryName: String
    private lateinit var categoryMvvm: CategoryMealsDetailsViewModel
    private lateinit var categoryAdapter: CategoriesMealsDetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        categoryMvvm = ViewModelProviders.of(this)[CategoryMealsDetailsViewModel::class.java]
        categoryAdapter = CategoriesMealsDetailAdapter()

        prepareCategoryDetailsRecyclerView()
        getCategoryName()
        categoryMvvm.getCategoryMealsList(categoryName)
        observeCategoryMealsLiveData()


    }

    private fun prepareCategoryDetailsRecyclerView() {
        binding.rvCategoryDetails.apply {
            layoutManager = GridLayoutManager(this@CategoryDetailActivity, 2)
            adapter = categoryAdapter
        }
    }

    private fun observeCategoryMealsLiveData() {
        categoryMvvm.observeCategoryMealsLiveData().observe(this) { categories ->
            categoryAdapter.setCategoryMeals(categories)
        }
    }


    private fun getCategoryName() {
        val intent = intent
        categoryName = intent.getStringExtra(HomeFragment.CATEGORY_NAME)!!
        binding.tvCategoryTitleDetail.text = categoryName
    }
}