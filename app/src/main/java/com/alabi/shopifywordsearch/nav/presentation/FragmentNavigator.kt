package com.alabi.shopifywordsearch.nav.presentation

import com.alabi.shopifywordsearch.R
import com.alabi.shopifywordsearch.difficulty.ui.DifficultyFragment
import com.alabi.shopifywordsearch.game.ui.GameFragment
import com.alabi.shopifywordsearch.nav.ui.NavActivity
import com.alabi.shopifywordsearch.util.extensions.addFragment
import com.alabi.shopifywordsearch.util.extensions.addFragmentToBackStack
import com.alabi.shopifywordsearch.util.search.Difficulty
import com.alabi.shopifywordsearch.welcome.ui.WelcomeFragment

/**
 * Simple navigator class for navigating between fragments
 * @author Samer Alabi
 */
class FragmentNavigator(private val activity: NavActivity) {
    /**
     * Navigates to welcome fragment
     */
    fun toWelcome() {
        activity.addFragment(WelcomeFragment.TAG, R.id.fragment_container) {
            WelcomeFragment.newInstance()
        }
    }

    /**
     * Navigates to difficulty fragment
     */
    fun toDifficulty() {
        activity.addFragmentToBackStack(DifficultyFragment.TAG, R.id.fragment_container) {
            DifficultyFragment.newInstance()
        }
    }

    /**
     * Navigates to game fragment
     * @param difficulty is the difficulty the game will be played at
     */
    fun toGame(difficulty: Difficulty) {
        activity.addFragmentToBackStack(GameFragment.TAG, R.id.fragment_container) {
            GameFragment.newInstance(difficulty)
        }
    }
}