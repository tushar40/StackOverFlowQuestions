package com.example.stkovrfloquestions.utils

import android.os.Bundle

interface FragmentListener {
    fun navigateFragment(actionID: Int?, destination: Int?, bundle: Bundle?)
}