package com.tunahanozatac.soundplay.ui.splashscreen

import android.animation.Animator
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import com.tunahanozatac.soundplay.R
import com.tunahanozatac.soundplay.base.BaseFragment
import com.tunahanozatac.soundplay.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSplash()
    }

    private fun initSplash() {
        binding.splashAnimation.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                Log.v("Animation", "Started")
            }

            override fun onAnimationEnd(animation: Animator) {
                findNavController().navigate(R.id.action_splashFragment_to_searchFragment)
            }

            override fun onAnimationCancel(animation: Animator) {
                Log.v("Animation", "Canceled")
            }

            override fun onAnimationRepeat(animation: Animator) {
                Log.v("Animation", "Repeated")
            }

        })
    }

    override fun isBottomNavigationBarVisible(): Boolean {
        return false
    }
}