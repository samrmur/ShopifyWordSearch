package com.alabi.shopifywordsearch.welcome.di

import com.alabi.shopifywordsearch.core.core.FragmentScope
import com.alabi.shopifywordsearch.welcome.ui.WelcomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class WelcomeFragmentProvider {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun providesWelcomeFragment(): WelcomeFragment
}