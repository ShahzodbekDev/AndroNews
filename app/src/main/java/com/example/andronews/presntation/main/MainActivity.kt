package com.example.andronews.presntation.main

import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUi()
        subscribeToLiveData()

    }

    private  fun subscribeToLiveData(){
        viewModel.events.observe(this){
            when(it){
                is MainViewModel.Event.NavigateTo -> navigateTo(it.destination)
            }

        }
    }

    private fun navigateTo(destination: Destination){
        when(destination){
            Destination.Home -> navController.navigate(MainDirections.toHomeFragment())
            Destination.SignIn -> navController.navigate(MainDirections.toSignInFragment())
            Destination.Splash -> navController.navigate(MainDirections.toSplashFragment())
        }
    }

    private fun initUi() = with(binding) {
        bottomNavigation.setupWithNavController(navController)
    }
}