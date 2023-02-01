package me.mazzampr.infojajanan2.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import me.mazzampr.infojajanan2.R
import me.mazzampr.infojajanan2.activities.CategoryDetailActivity
import me.mazzampr.infojajanan2.activities.MainActivity
import me.mazzampr.infojajanan2.adapters.CategoriesAdapter
import me.mazzampr.infojajanan2.databinding.FragmentCategoriesBinding
import me.mazzampr.infojajanan2.viewModel.HomeViewModel

class CategoriesFragment : Fragment() {
    private lateinit var binding: FragmentCategoriesBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var categoriesAdapter: CategoriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        categoriesAdapter = CategoriesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareCategoriesRecyclerView()
        observeCategoriesLiveData()
        onCategoriesItemClicked()
    }

    private fun onCategoriesItemClicked() {
        categoriesAdapter.onItemClicked = { categoriesItem ->
            val intent = Intent(activity, CategoryDetailActivity::class.java)
            intent.putExtra(HomeFragment.CATEGORY_NAME, categoriesItem.strCategory)
            startActivity(intent)
        }
    }

    private fun observeCategoriesLiveData() {
        viewModel.observeCategoriesLiveData().observe(viewLifecycleOwner) { categories ->
            categoriesAdapter.setCategoriesItem(categories)
        }
    }

    private fun prepareCategoriesRecyclerView() {
        binding.rvCategories.apply {
            layoutManager = GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)
            adapter = categoriesAdapter
        }
    }

}