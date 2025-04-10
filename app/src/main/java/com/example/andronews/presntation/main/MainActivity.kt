package com.example.andronews.presntation.main

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Bundle
import android.util.TypedValue
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.andronews.MainDirections
import com.example.andronews.R
import com.example.andronews.databinding.ActivityMainBinding
import com.example.andronews.domain.model.Destination
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var binding: ActivityMainBinding
    private val navController get() = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
    private val viewModel by viewModels<MainViewModel>()


    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        val menuView = bottomNav.getChildAt(0) as BottomNavigationMenuView

        for (i in 0 until menuView.childCount) {
            val itemView = menuView.getChildAt(i)
            itemView.setBackgroundResource(R.drawable.bottom_nav_item_bg)

            val layoutParams = itemView.layoutParams
            layoutParams.width = dpToPx(60)
            layoutParams.height = dpToPx(30)
            itemView.layoutParams = layoutParams

            itemView.setPadding(0, 10, 0, 10)

        }


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
        when(destination){
            Destination.Home -> navController.navigate(MainDirections.toHomeFragment())
            Destination.SignIn -> navController.navigate(MainDirections.toSignInFragment())
            Destination.Splash -> navController.navigate(MainDirections.toSplashFragment())
        }
    }

    private fun initUi() = with(binding) {

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment, R.id.myInterestsFragment, R.id.saveFragment, R.id.profileFragment
            ), drawerLayout
        )

        val drawerToggle = ActionBarDrawerToggle(
            this@MainActivity, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerToggle.setHomeAsUpIndicator(R.drawable.ic_toolbar_hamburger)
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()


        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)


        navView.setupWithNavController(navController)
        bottomNavigation.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    private fun dpToPx(dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            Resources.getSystem().displayMetrics
        ).toInt()
    }



}


