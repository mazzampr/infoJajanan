package me.mazzampr.infojajanan2.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import me.mazzampr.infojajanan2.R
import me.mazzampr.infojajanan2.databinding.ActivityDetailBinding
import me.mazzampr.infojajanan2.fragments.HomeFragment
import me.mazzampr.infojajanan2.pojo.Meal
import me.mazzampr.infojajanan2.viewModel.DetailMealViewModel

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
        mealMvvm = ViewModelProviders.of(this)[DetailMealViewModel::class.java]

        getMealInformationIntent()
        setInformationInView()

        mealMvvm.getMealDetail(mealId)
        observeMealDetailLiveData()
    }

    private fun observeMealDetailLiveData() {
        mealMvvm.observeMealDetailLiveData().observe(this
        ) { t ->
            val meal = t
            binding.tvTitle.text = meal!!.strMeal
            binding.tvCategory.text = meal!!.strCategory
            binding.tvInstructions.text = meal!!.strInstructions
        }
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