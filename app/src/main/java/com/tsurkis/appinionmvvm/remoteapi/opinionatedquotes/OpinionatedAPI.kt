package com.tsurkis.appinionmvvm.remoteapi.opinionatedquotes

import com.tsurkis.appinionmvvm.remoteapi.opinionatedquotes.responseobjects.RandomOpinionatedQuotesResponseObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpinionatedAPI {

    @GET("/v1/quotes")
    fun requestRandomOpinionatedQuotes(
            @Query("rand") isRandom: String,
            @Query("n") numberOfQuotes: Int
    ): Call<RandomOpinionatedQuotesResponseObject>
}