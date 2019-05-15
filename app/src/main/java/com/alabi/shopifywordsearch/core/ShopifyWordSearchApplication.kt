package com.alabi.shopifywordsearch.core

import com.alabi.shopifywordsearch.core.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class ShopifyWordSearchApplication: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerApplicationComponent.factory().create(this)
}