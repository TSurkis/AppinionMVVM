package com.tsurkis.appinionmvvm.dependency.injection

import com.tsurkis.appinionmvvm.screens.quotes.QuotesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ScreenBindingModule {

    @ContributesAndroidInjector
    abstract fun quotesActivity(): QuotesActivity
}