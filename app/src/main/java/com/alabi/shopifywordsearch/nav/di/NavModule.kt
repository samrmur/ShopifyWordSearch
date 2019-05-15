package com.alabi.shopifywordsearch.nav.di

import com.alabi.shopifywordsearch.core.core.ActivityScope
import com.alabi.shopifywordsearch.nav.presentation.FragmentNavigator
import com.alabi.shopifywordsearch.nav.ui.NavActivity
import dagger.Module
import dagger.Provides

@Module
class NavModule {
    @ActivityScope
    @Provides
    fun providesNavigator(activity: NavActivity): FragmentNavigator = FragmentNavigator(activity)
}