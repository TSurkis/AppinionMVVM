package com.tsurkis.appinionmvvm.screens.quotes

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.tsurkis.appinionmvvm.application.ThreadManager
import com.tsurkis.appinionmvvm.remoteapi.opinionatedquotes.responseobjects.Quote
import com.tsurkis.appinionmvvm.repositories.OpinionatedQuotesRepository
import javax.inject.Inject

class QuotesViewModel @Inject constructor(
        private val threadManager: ThreadManager,
        private val repository: OpinionatedQuotesRepository
) : ViewModel() {

    private var screenState: MutableLiveData<ScreenState> = MutableLiveData()
    private var quotes: MutableLiveData<List<Quote>> = MutableLiveData()

    private val timeFrameBetweenRequestsInMilliseconds: Long = 5000
    private var lastRequestTimeFrame: Long = 0L

    fun getObservedScreenState(): LiveData<ScreenState> = screenState

    fun getObservedQuotesDataCollection(): LiveData<List<Quote>> = quotes

    fun onViewInitialized() {
        //
        // If the lastRequestTimeFrame field is 0 then it means that the view  was
        // just initialized. Thus, a new call is being made for quotes.
        // If the view  was rotated, this line would not occur since the call already
        // happened.
        if (lastRequestTimeFrame == 0L) {
            requestRandomQuotes()
        }
    }

    fun requestRandomQuotes() {
        if (requestIsInTimeFrame()) {
            updateTimeFrame()
            screenState.postValue(
                    ScreenState(
                            showProgressBar = true,
                            showListOfQuotes = false,
                            showTimeFrameError = false))
            threadManager.backThread.execute {
                repository.requestOpinionatedQuotes(::onSuccess)
            }
        } else {
            screenState.postValue(
                    ScreenState(
                            showProgressBar = false,
                            showListOfQuotes = true,
                            showTimeFrameError = true,
                            timeLeftToMakeNextRequest = getRemainingTimeUntilNextRequest()))
        }
    }

    private fun onSuccess(newQuotes: List<Quote>) {
        screenState.postValue(
                ScreenState(
                        showProgressBar = false,
                        showListOfQuotes = true,
                        showTimeFrameError = false))
        quotes.postValue(newQuotes)
    }

    private fun updateTimeFrame() {
        lastRequestTimeFrame = System.currentTimeMillis()
    }

    private fun requestIsInTimeFrame() =
            System.currentTimeMillis() - lastRequestTimeFrame >= timeFrameBetweenRequestsInMilliseconds

    private fun getRemainingTimeUntilNextRequest() =
            lastRequestTimeFrame - System.currentTimeMillis() + timeFrameBetweenRequestsInMilliseconds
}