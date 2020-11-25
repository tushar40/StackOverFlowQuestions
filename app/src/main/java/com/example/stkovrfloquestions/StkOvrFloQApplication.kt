package com.example.stkovrfloquestions

import android.app.Application

class StkOvrFloQApplication: Application() {

    /**
     * Application Lifecycle Method
     */

    override fun onCreate() {
        super.onCreate()

        QuestionRepository.initRepository()
    }
}