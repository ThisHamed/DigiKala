package com.example.digikala.di

import com.example.digikala.data.db.CartDao
import com.example.digikala.data.db.DigikalaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CartDaoModule {

    @Provides
    @Singleton
    fun provideCartDao(
        database: DigikalaDatabase
    ): CartDao = database.cartDao()
}
