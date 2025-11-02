package com.example.digikala.di

import android.content.Context
import com.example.digikala.data.datastore.DatastoreRepository
import com.example.digikala.data.datastore.DatastoreRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatastoreModule {

    @Singleton
    @Provides
    fun provideDatastoreRepository(
        @ApplicationContext context: Context
    ): DatastoreRepository = DatastoreRepositoryImpl(context)
}