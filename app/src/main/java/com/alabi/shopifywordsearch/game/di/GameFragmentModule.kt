package com.alabi.shopifywordsearch.game.di

import com.alabi.shopifywordsearch.core.core.FragmentScope
import com.alabi.shopifywordsearch.game.presentation.GameView
import com.alabi.shopifywordsearch.game.ui.GameFragment
import dagger.Module
import dagger.Provides

@Module
class GameFragmentModule {
    @FragmentScope
    @Provides
    fun providesGameView(fragment: GameFragment): GameView = fragment
}