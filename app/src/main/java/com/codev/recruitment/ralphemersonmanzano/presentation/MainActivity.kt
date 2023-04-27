package com.codev.recruitment.ralphemersonmanzano.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.codev.recruitment.ralphemersonmanzano.R
import com.codev.recruitment.ralphemersonmanzano.databinding.ActivityMainBinding
import com.codev.recruitment.ralphemersonmanzano.details.R.*
import com.codev.recruitment.ralphemersonmanzano.navigation.HomeNavigator
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        val navView: BottomNavigationView = binding.bottomNavView
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                id.fragment_details, id.fragment_form -> {
                    navView.isVisible = false
                    binding.toolbar.isVisible = false
                }
                else -> {
                    navView.isVisible = true
                    binding.toolbar.isVisible = true
                }
            }
        }

        setupUI()
    }

    private fun setupUI() = with(binding) {

    }
}