package com.alabi.shopifywordsearch.core.component

import com.alabi.shopifywordsearch.core.ShopifyWordSearchApplication
import com.alabi.shopifywordsearch.core.module.ActivityBuilder
import com.alabi.shopifywordsearch.core.module.WordSearchModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBuilder::class,
    WordSearchModule::class
])
interface ApplicationComponent: AndroidInjector<ShopifyWordSearchApplication> {
    @Component.Factory
    abstract class Factory: AndroidInjector.Factory<ShopifyWordSearchApplication>
}