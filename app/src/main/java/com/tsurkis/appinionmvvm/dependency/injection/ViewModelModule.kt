package com.tsurkis.appinionmvvm.dependency.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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