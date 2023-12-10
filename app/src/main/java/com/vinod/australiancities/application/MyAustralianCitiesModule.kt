package com.vinod.australiancities.application

import android.content.Context
import com.vinod.australiancitieslibrary.repositories.CitiesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MyCitiesModule {

    @Provides
    @Singleton
    fun provideCityRepository(@ApplicationContext context: Context) = CitiesRepository(context)
}