package com.example.stkovrfloquestions.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.stkovrfloquestions.R
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

    override fun navigateFragment(actionID: Int?, destination: Int?, bundle: Bundle?) {
        val controller = Navigation.findNavController(findViewById(R.id.host_fragment))
        if (actionID == null) {
            if (destination != null) {
                controller.popBackStack(destination, false)
            } else {
                controller.popBackStack()
            }
        } else {
            try {
                controller.navigate(actionID, bundle)
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun makeToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}