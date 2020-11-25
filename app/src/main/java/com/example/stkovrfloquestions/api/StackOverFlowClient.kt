package com.example.stkovrfloquestions.api

import com.example.stkovrfloquestions.utils.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object StackOverFlowClient {

    /**
     * Member variables
     */

    private var api: APIInterface? = null

    /**
     * Public Methods
     */

    fun getApi(): APIInterface {
        if (api == null) {
            val retrofit = getRetrofit()
            api = retrofit.create(APIInterface::class.java)
        }

        return api!!
    }

    /**
     * Private Methods
     */

    private fun getHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
        client.addInterceptor(loggingInterceptor)

        return client.build()
    }

    private fun getRetrofit(): Retrofit {
        val baseURL = Constants.BASE_URL
        val client =  Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .baseUrl(baseURL)
            .client(getHttpClient())

        return client.build()
    }
}