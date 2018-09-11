package com.tsurkis.appinionmvvm.screens.quotes

class ScreenState(
        val showProgressBar: Boolean,
        val showListOfQuotes: Boolean,
        val showTimeFrameError: Boolean,
        val timeLeftToMakeNextRequest: Long? = null
)