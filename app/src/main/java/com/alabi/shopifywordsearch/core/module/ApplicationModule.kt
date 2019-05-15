package com.alabi.shopifywordsearch.core.module

import android.content.Context
import com.alabi.shopifywordsearch.core.ShopifyWordSearchApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {
    @Singleton
    @Provides
    fun providesApplication(application: ShopifyWordSearchApplication): ShopifyWordSearchApplication = application

    @Singleton
    @Provides
    fun providesApplicationContext(application: ShopifyWordSearchApplication): Context = application.applicationContext
}