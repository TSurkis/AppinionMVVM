package com.tsurkis.appinionmvvm.remoteapi.opinionatedquotes.responseobjects

import com.google.gson.annotations.SerializedName

data class RandomOpinionatedQuotesResponseObject(
        @SerializedName("quotes")
        val quotes: List<Quote>
)

data class Quote(
        @SerializedName("quote")
        val quote: String,

        @SerializedName("author")
        val author: String
)