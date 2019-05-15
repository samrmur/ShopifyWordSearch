package com.alabi.shopifywordsearch.difficulty.di

import com.alabi.shopifywordsearch.core.core.FragmentScope
import com.alabi.shopifywordsearch.difficulty.ui.DifficultyFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DifficultyFragmentProvider {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun providesDifficultyFragment(): DifficultyFragment
}