package com.example.stkovrfloquestions

import android.app.Application
import com.example.stkovrfloquestions.repositories.QuestionRepository

class StkOvrFloQApplication: Application() {

    /**
     * Application Lifecycle Method
     */

    override fun onCreate() {
        super.onCreate()

        QuestionRepository.initRepository(applicationContext)
    }
}