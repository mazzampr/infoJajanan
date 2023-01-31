package me.mazzampr.infojajanan2.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import me.mazzampr.infojajanan2.R
import me.mazzampr.infojajanan2.databinding.ActivityDetailBinding
import me.mazzampr.infojajanan2.db.MealDatabase
import me.mazzampr.infojajanan2.fragments.HomeFragment
import me.mazzampr.infojajanan2.pojo.Meal
import me.mazzampr.infojajanan2.viewModel.DetailMealViewModel
import me.mazzampr.infojajanan2.viewModel.MealViewModelFactory

class DetailActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityDetailBinding
    private lateinit var mealName: String
    private lateinit var mealThumb: String
    private lateinit var mealId: String
    private lateinit var mealMvvm: DetailMealViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mealDatabase = MealDatabase.getInstance(this)
        val viewModelFactory = MealViewModelFactory(mealDatabase)
        mealMvvm = ViewModelProvider(this, viewModelFactory)[DetailMealViewModel::class.java]
//        mealMvvm = ViewModelProviders.of(this)[DetailMealViewModel::class.java]

        getMealInformationIntent()
        setInformationInView()

        mealMvvm.getMealDetail(mealId)
        observeMealDetailLiveData()

        onFavoriteClick()
    }

    private fun onFavoriteClick() {
        binding.btnFavorite.setOnClickListener {
            mealToSave?.let {
                    if (!it.strFavorite) {
                        it.strFavorite = true
                        mealMvvm.insertMeal(it)
                        Toast.makeText(this, "Meal Liked", Toast.LENGTH_SHORT).show()
                        binding.btnFavorite.setImageResource(R.drawable.ic__favorite_fill)
                    } else {
                        it.strFavorite = false
                        mealMvvm.deleteMeal(it)
                        Toast.makeText(this, "Meal Unliked", Toast.LENGTH_SHORT).show()
                        binding.btnFavorite.setImageResource(R.drawable.ic_favorite_white)
                    }
            }
        }
    }

    private var mealToSave: Meal? = null

    private fun observeMealDetailLiveData() {
        mealMvvm.observeMealDetailLiveData().observe(this, object : Observer<Meal>{
            override fun onChanged(t: Meal?) {
                val meal = t
                mealToSave = meal
                binding.tvTitle.text = meal!!.strMeal
                binding.tvCategory.text = meal!!.strCategory
                binding.tvInstructions.text = meal!!.strInstructions
                if(meal.strFavorite) {
                    binding.btnFavorite.setImageResource(R.drawable.ic__favorite_fill)
                }
            }
        })
    }

    private fun setInformationInView() {
        Glide.with(applicationContext)
            .load(mealThumb)
            .into(binding.imgMealDetail)
        binding.collapsingToolbar.title = mealName
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))
    }
    private fun getMealInformationIntent() {
        val intent = intent
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID)!!
        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        mealThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB)!!
    }
}