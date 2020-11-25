package com.example.stkovrfloquestions.api

import com.example.stkovrfloquestions.models.Questions
import com.example.stkovrfloquestions.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {

    /**
     * GET requests
     */

    @GET(Constants.SEARCH_ENDPOINT)
    suspend fun getTweets(@Query("order") order: String,
                          @Query("sort") sort: String,
                          @Query("accepted") accepted: String,
                          @Query("answers") answers: Int,
                          @Query("site") site: String)
            : Response<Questions>
}