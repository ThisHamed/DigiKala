package com.example.digikala.di

import com.example.digikala.data.remote.AddressApiInterface
import com.example.digikala.data.remote.ProductDetailApiInterface
import com.example.digikala.data.remote.ProfileApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductDetailApiInterfaceModule {

    @Singleton
    @Provides
    fun provideProductDetailApiService(retrofit: Retrofit): ProductDetailApiInterface =
        retrofit.create(ProductDetailApiInterface::class.java)

}