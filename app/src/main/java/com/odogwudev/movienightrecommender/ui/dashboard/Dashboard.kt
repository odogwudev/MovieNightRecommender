package com.odogwudev.movienightrecommender.ui.dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.odogwudev.movienightrecommender.R
import com.odogwudev.movienightrecommender.databinding.ActivityDashboardBinding
import com.odogwudev.movienightrecommender.ui.dashboard.fragments.search.SearchFragment
import com.odogwudev.movienightrecommender.ui.userProfile.UserProfile
import com.odogwudev.movienightrecommender.ui.userProfile.UserViewModel

class Dashboard : AppCompatActivity() {

    private lateinit var searchFragment: SearchFragment
    private lateinit var binding: ActivityDashboardBinding
    private var viewModel = UserViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        val navView: BottomNavigationView = findViewById(R.id.bottom_nav)
        val navController = findNavController(R.id.fragment)

        navView.setupWithNavController(navController)

        viewModel.getUserPhoto()

        binding.userProfileImg.setOnClickListener {
            startActivity(Intent(this, UserProfile::class.java))
        }

        binding.searchView.setOnClickListener {
            binding.searchView.isIconified = false
        }

        binding.searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchView.clearFocus()
                binding.searchView.setQuery("", false)
                Log.d("HELLO", query!!)
                val bundle = bundleOf("QUERY" to query)
                searchFragment =
                    SearchFragment()
                supportFragmentManager.beginTransaction().replace(R.id.fragment, searchFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
                searchFragment.arguments = bundle
                d("BUNDLE", bundle.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.imageDownloadLink.observe(this, Observer<String> { img ->
            requireNotNull("profile image path", { img })
            Glide.with(this).load(img).into(binding.userProfileImg)
        })
    }

    override fun onBackPressed() {
        // You can't go back | Logout if you want to leave
    }
}