package com.tsurkis.appinionmvvm.dependency.injection

import com.tsurkis.appinionmvvm.application.ThreadManager
import com.tsurkis.appinionmvvm.dependency.injection.annotations.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ThreadingModule {

    @Provides
    @ApplicationScope
    fun provideThreadManager() = ThreadManager()
}