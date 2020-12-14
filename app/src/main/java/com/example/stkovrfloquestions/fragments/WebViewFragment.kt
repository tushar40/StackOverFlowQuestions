package com.example.stkovrfloquestions.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.example.stkovrfloquestions.R
import kotlinx.android.synthetic.main.fragment_web_view.*

class WebViewFragment: Fragment() {

    /**
     * Member variables
     */

    private lateinit var url: String

    /**
     * Lifecycle Methods
     */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_web_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val safeArgs = WebViewFragmentArgs.fromBundle(it)
            url = safeArgs.quesUrl
        }
        setUpUI()
    }

    /**
     * Custom Methods
     */

    @SuppressLint("SetJavaScriptEnabled")
    private fun setUpUI() {
        webView_question.loadUrl(url)
        webView_question.webViewClient = WebViewClient()
        webView_question.settings.javaScriptEnabled = true
        webView_question.settings.setSupportZoom(true)
        webView_question.settings.builtInZoomControls = true
        webView_question.settings.displayZoomControls = false
    }
}