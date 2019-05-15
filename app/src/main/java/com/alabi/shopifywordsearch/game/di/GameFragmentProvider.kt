package com.alabi.shopifywordsearch.game.di

import com.alabi.shopifywordsearch.core.core.FragmentScope
import com.alabi.shopifywordsearch.game.ui.GameFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class GameFragmentProvider {
    @FragmentScope
    @ContributesAndroidInjector(modules = [
        GameFragmentModule::class
    ])
    abstract fun providesGameFragment(): GameFragment
}