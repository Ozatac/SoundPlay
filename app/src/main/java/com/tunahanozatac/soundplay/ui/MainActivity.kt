package com.tunahanozatac.soundplay.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.tunahanozatac.soundplay.utils.gone
import com.tunahanozatac.soundplay.utils.show
import com.tunahanozatac.soundplay.R
import com.tunahanozatac.soundplay.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    fun showNavigationBar() {
        binding.bottomNavigationView.show()
    }

    fun hideNavigationBar() {
        binding.bottomNavigationView.gone()
    }
}