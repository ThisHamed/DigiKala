package com.example.digikala.di

import com.example.digikala.data.db.CartDao
import com.example.digikala.data.db.DigikalaDatabase
import com.example.digikala.data.db.FavoriteListDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavoriteDaoModule {

    @Provides
    @Singleton
    fun provideFavoriteDao(
        database: DigikalaDatabase
    ): FavoriteListDao = database.favoriteDao()
}
