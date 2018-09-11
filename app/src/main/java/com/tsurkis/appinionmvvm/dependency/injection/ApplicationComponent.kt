package com.tsurkis.appinionmvvm.dependency.injection

import android.content.Context
import com.tsurkis.appinionmvvm.application.App
import com.tsurkis.appinionmvvm.dependency.injection.annotations.ApplicationContext
import com.tsurkis.appinionmvvm.dependency.injection.annotations.ApplicationScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@ApplicationScope
@Component(modules = [
    AndroidInjectionModule::class,
    ScreenBindingModule::class,
    ThreadingModule::class,
    RemoteAPIModule::class,
    ViewModelModule::class
])
interface ApplicationComponent : AndroidInjector<App> {

    override fun inject(instance: App)

    @Component.Builder
    interface Builder {
        fun build(): ApplicationComponent
        @BindsInstance
        fun application(@ApplicationContext applicationContext: Context): Builder
    }
}