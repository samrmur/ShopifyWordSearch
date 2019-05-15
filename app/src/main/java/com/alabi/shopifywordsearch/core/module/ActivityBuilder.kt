package com.alabi.shopifywordsearch.core.module

import com.alabi.shopifywordsearch.core.core.ActivityScope
import com.alabi.shopifywordsearch.difficulty.di.DifficultyFragmentProvider
import com.alabi.shopifywordsearch.game.di.GameFragmentProvider
import com.alabi.shopifywordsearch.nav.di.NavModule
import com.alabi.shopifywordsearch.nav.ui.NavActivity
import com.alabi.shopifywordsearch.welcome.di.WelcomeFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ActivityScope
    @ContributesAndroidInjector(modules = [
        NavModule::class,
        WelcomeFragmentProvider::class,
        DifficultyFragmentProvider::class,
        GameFragmentProvider::class
    ])
    abstract fun bindsNavActivity(): NavActivity
}