package me.mazzampr.infojajanan2.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import me.mazzampr.infojajanan2.activities.CategoryDetailActivity
import me.mazzampr.infojajanan2.activities.DetailActivity
import me.mazzampr.infojajanan2.activities.MainActivity
import me.mazzampr.infojajanan2.adapters.CategoriesAdapter
import me.mazzampr.infojajanan2.adapters.PopularMealAdapter
import me.mazzampr.infojajanan2.databinding.FragmentHomeBinding
import me.mazzampr.infojajanan2.pojo.MealsByCategory
import me.mazzampr.infojajanan2.pojo.Meal
import me.mazzampr.infojajanan2.viewModel.HomeViewModel


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var randomMeal: Meal
    private lateinit var popularItemsAdapter: PopularMealAdapter
    private lateinit var categoriesAdapter: CategoriesAdapter

    companion object{
        const val MEAL_ID = "me.mazzampr.infojajanan2.fragments.idMeal"
        const val MEAL_NAME = "me.mazzampr.infojajanan2.fragments.nameMeal"
        const val MEAL_THUMB = "me.mazzampr.infojajanan2.fragments.thumbMeal"
        const val CATEGORY_NAME = "me.mazzampr.infojajanan2.fragments.categoryName"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//      homeMvvm = ViewModelProviders.of(this)[HomeViewModel::class.java]

        viewModel = (activity as MainActivity).viewModel
        popularItemsAdapter = PopularMealAdapter()
        categoriesAdapter = CategoriesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preparePopularItemsRecyclerView()
        prepareCategoriesRecyclerView()

        viewModel.getCategories()
        observeCategoriesLiveData()
        onCategoriesItemClicked()

        viewModel.getRandomMeal()
        observerRandomMeal()
        onRandomMealClick()

        viewModel.getPopularMeals()
        observePopularItemsLiveData()
        onPopularItemClick()

    }

    private fun onCategoriesItemClicked() {
        categoriesAdapter.onItemClicked = { categoriesItem ->
            val intent = Intent(activity, CategoryDetailActivity::class.java)
            intent.putExtra(CATEGORY_NAME, categoriesItem.strCategory)
            startActivity(intent)
        }
    }

    private fun prepareCategoriesRecyclerView() {
        binding.rvCategories.apply {
            layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
            adapter = categoriesAdapter
        }
    }

    private fun observeCategoriesLiveData() {
        viewModel.observeCategoriesLiveData().observe(viewLifecycleOwner
        ) { categories ->
                categoriesAdapter.setCategoriesItem(categories)
        }
    }

    private fun onPopularItemClick() {
        popularItemsAdapter.onItemClick = {meal ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(MEAL_ID, meal.idMeal)
            intent.putExtra(MEAL_NAME, meal.strMeal)
            intent.putExtra(MEAL_THUMB, meal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun preparePopularItemsRecyclerView() {
        binding.rvPopularMeal.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularItemsAdapter
        }
    }

    private fun observePopularItemsLiveData() {
        viewModel.observePopularItemsLiveData().observe(viewLifecycleOwner
        ) { mealList ->
            popularItemsAdapter.setMeals(mealsList = mealList as ArrayList<MealsByCategory>)
        }
    }

    private fun onRandomMealClick() {
        binding.imgRandomMealCard.setOnClickListener {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(MEAL_ID, randomMeal.idMeal)
            intent.putExtra(MEAL_NAME, randomMeal.strMeal)
            intent.putExtra(MEAL_THUMB, randomMeal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun observerRandomMeal() {
        viewModel.observeRandomMealLiveData().observe(viewLifecycleOwner
        ) { meal ->
            Glide.with(this@HomeFragment)
                .load(meal.strMealThumb)
                .into(binding.imgRandomMeal)

            this.randomMeal = meal
        }
    }


}