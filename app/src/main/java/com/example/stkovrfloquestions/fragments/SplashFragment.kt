package com.example.stkovrfloquestions.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.stkovrfloquestions.R
import com.example.stkovrfloquestions.utils.FragmentListener

class SplashFragment: Fragment() {

    /**
     * Property Variables
     */

    private val SPLASH_DELAY = 500L
    private val callback by lazy {
        activity as FragmentListener
    }

    /**
     * Lifecycle Methods
     */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gotoQuestionsFragment()
    }

    /**
     * Custom Methods
     */

    fun gotoQuestionsFragment() {
        Handler(Looper.getMainLooper()).postDelayed({
            callback.navigateFragment(R.id.action_splashFragment_to_questionsFragment, null, null)
        }, SPLASH_DELAY)
    }

}