package com.alabi.shopifywordsearch.welcome.presentation

import com.alabi.shopifywordsearch.nav.presentation.FragmentNavigator
import javax.inject.Inject

/**
 * Presenter to perform tasks that affect Welcome UI
 * @author Samer Alabi
 */
class WelcomePresenter @Inject constructor(private val navigator: FragmentNavigator) {
    /**
     * Navigates to difficulty fragment
     */
    fun toDifficulty() {
        navigator.toDifficulty()
    }
}