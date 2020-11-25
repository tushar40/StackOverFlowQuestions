package com.example.stkovrfloquestions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import com.example.stkovrfloquestions.utils.FragmentListener

class MainActivity: AppCompatActivity(), FragmentListener {

    /**
     * Lifecycle Methods
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * Fragment Listener Methods
     */

    override fun navigateFragment(actionID: Int?, destination: Int?) {
        val controller = Navigation.findNavController(findViewById(R.id.host_fragment))
        if (actionID == null) {
            if (destination != null) {
                controller.popBackStack(destination, false)
            } else {
                controller.popBackStack()
            }
        } else {
            try {
                controller.navigate(actionID)
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }
}