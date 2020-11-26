package com.example.stkovrfloquestions.repositories

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.stkovrfloquestions.api.APIInterface
import com.example.stkovrfloquestions.api.StackOverFlowClient
import com.example.stkovrfloquestions.models.Item
import com.example.stkovrfloquestions.utils.Constants
import java.text.SimpleDateFormat
import java.util.*

object QuestionRepository {

    /**
     * Property variables
     */

    private val LOG_TAG = QuestionRepository::class.simpleName
    private lateinit var tweetsApi: APIInterface

    val questionsData = MutableLiveData<List<Item>>()

    /**
     * Member functions
     */

    fun initRepository(context: Context) {
        tweetsApi = StackOverFlowClient.getApi(context)
    }

    suspend fun getQuestions(minAnswers: Int) {
        val response = tweetsApi.getTweets(
            order = Constants.order,
            sort = Constants.sort,
            accepted = Constants.accepted,
            answers = minAnswers,
            site = Constants.site
        )

        if (response.isSuccessful && response.body() != null) {
            questionsData.postValue(response.body()!!.items)
        } else {
            Log.e(LOG_TAG, "couldn't get the data, error: ${response.message()}")
        }
    }
}