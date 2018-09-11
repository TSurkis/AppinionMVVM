package com.tsurkis.appinionmvvm.dependency.injection

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.tsurkis.appinionmvvm.application.ViewModelFactory
import com.tsurkis.appinionmvvm.dependency.injection.annotations.ViewModelKey
import com.tsurkis.appinionmvvm.screens.quotes.QuotesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(QuotesViewModel::class)
    abstract fun provideQuotesViewModel(quotesViewModel: QuotesViewModel): ViewModel
}