package com.alabi.shopifywordsearch.nav.presentation

import javax.inject.Inject

/**
 * Presenter for performing changes to Nav UI
 * @author Samer Alabi
 */
class NavPresenter @Inject constructor(private val navigator: FragmentNavigator) {
    /**
     * Navigates to welcome fragment
     */
    fun toWelcome() {
        navigator.toWelcome()
    }
}