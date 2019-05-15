package com.alabi.shopifywordsearch.difficulty.presentation

import com.alabi.shopifywordsearch.nav.presentation.FragmentNavigator
import com.alabi.shopifywordsearch.util.search.Difficulty
import javax.inject.Inject

/**
 * Presenter for difficulty fragment
 * @author Samer Alabi
 */
class DifficultyPresenter @Inject constructor(private val navigator: FragmentNavigator) {
    /**
     * Navigates to game fragment with easy difficulty set
     */
    fun toEasyGame() {
        navigator.toGame(Difficulty.EASY)
    }

    /**
     * Navigates to game fragment with medium difficulty set
     */
    fun toMediumGame() {
        navigator.toGame(Difficulty.MEDIUM)
    }

    /**
     * Navigates to game fragment with hard difficulty set
     */
    fun toHardGame() {
        navigator.toGame(Difficulty.HARD)
    }
}