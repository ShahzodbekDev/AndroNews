package com.example.andronews.presntation.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.andronews.MainDirections
import com.example.andronews.R
import com.example.andronews.databinding.ActivityMainBinding
import com.example.andronews.domain.model.Destination
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val navController get() = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
    private val viewModel by viewModels<MainViewModel>()


    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUi()
        subscribeToLiveData()

    }

    private fun subscribeToLiveData() {
        viewModel.events.observe(this) {
            when (it) {
                is MainViewModel.Event.NavigateTo -> navigateTo(it.destination)
            }

        }
    }

    private fun navigateTo(destination: Destination) {
        when (destination) {
            Destination.Home -> navController.navigate(MainDirections.toHomeFragment())
            Destination.SignIn -> navController.navigate(MainDirections.toSignInFragment())
            Destination.Splash -> navController.navigate(MainDirections.toSplashFragment())
            Destination.Interests -> navController.navigate(MainDirections.toInterestsFragment())
        }
    }

    private fun initUi() = with(binding) {

        navController.addOnDestinationChangedListener { _, destination, _ ->


            val hideDrawerFragments = listOf(
                R.id.splashFragment,
                R.id.signupFragment,
                R.id.signInFragment,
                R.id.interestsFragment,
                R.id.forgotPasswordFragment,
                R.id.detailsFragment,
                R.id.searchFragment
            )


            val hideBottomNavFragments = listOf(
                R.id.splashFragment,
                R.id.signupFragment,
                R.id.signInFragment,
                R.id.interestsFragment,
                R.id.forgotPasswordFragment,
                R.id.detailsFragment,
                R.id.searchFragment

            )


            if (destination.id in hideDrawerFragments) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            } else {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)

                setupToolbarWithDrawer()

                navView.setupWithNavController(navController)
                bottomNavigation.setupWithNavController(navController)
            }


            bottomNavigation.isVisible = destination.id !in hideBottomNavFragments
            toolbar.isVisible = destination.id !in hideDrawerFragments

            search.setOnClickListener {
                navController.navigate(MainDirections.toSearchFragment())
            }
        }

    }

    private fun setupToolbarWithDrawer() = with(binding) {

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)


        toolbar.navigationIcon =
            ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_toolbar_hamburger)

        toolbar.setNavigationOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
    }
}


