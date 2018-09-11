package com.tsurkis.appinionmvvm.dependency.injection

import com.tsurkis.appinionmvvm.BuildConfig
import com.tsurkis.appinionmvvm.dependency.injection.annotations.ApplicationScope
import com.tsurkis.appinionmvvm.remoteapi.opinionatedquotes.OpinionatedAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RemoteAPIModule {

    @Provides
    @ApplicationScope
    fun provideRetrofitCore(): Retrofit =
            Retrofit
                    .Builder()
                    .baseUrl(BuildConfig.OPINIONATED_API_END_POINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

    @Provides
    @ApplicationScope
    fun provideOpinionatedAPIRetrofitClient(retrofit: Retrofit): OpinionatedAPI =
            retrofit.create(OpinionatedAPI::class.java)

}