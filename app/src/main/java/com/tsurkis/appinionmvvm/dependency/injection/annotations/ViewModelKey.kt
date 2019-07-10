package com.tsurkis.appinionmvvm.dependency.injection.annotations

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
@MapKey
annotation class ViewModelKey(val keyValue: KClass<out ViewModel>)