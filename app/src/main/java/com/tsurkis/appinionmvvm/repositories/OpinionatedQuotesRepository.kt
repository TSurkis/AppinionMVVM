package com.tsurkis.appinionmvvm.repositories

import com.tsurkis.appinionmvvm.dependency.injection.annotations.ApplicationScope
import com.tsurkis.appinionmvvm.remoteapi.opinionatedquotes.OpinionatedAPIExecutor
import com.tsurkis.appinionmvvm.remoteapi.opinionatedquotes.responseobjects.Quote
import javax.inject.Inject

@ApplicationScope
class OpinionatedQuotesRepository @Inject constructor(
        private val opinionatedAPIExecutor: OpinionatedAPIExecutor
) {

    fun requestOpinionatedQuotes(resultBlock: (List<Quote>) -> (Unit)) {
        opinionatedAPIExecutor.requestRandomOpinionatedQuotes(
                isRandom = "t",
                numberOfQuotes = 10,
                successBlock = resultBlock)
    }
}