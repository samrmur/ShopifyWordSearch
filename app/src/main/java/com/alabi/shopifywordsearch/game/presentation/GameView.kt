package com.alabi.shopifywordsearch.game.presentation

import com.alabi.shopifywordsearch.util.search.Word

/**
 * Game view interface for communicating with presenter
 * @author Samer Alabi
 */
interface GameView {
    /**
     * Response function for when a word search game is created
     * @param game 2D board containing game
     * @param words list of words
     */
    fun onGameCreated(game: Array<CharArray>, words: ArrayList<Word>)

    /**
     * Response function for when a word is found
     * @param word simple word
     */
    fun onWordFound(word: Word)

    /**
     * Tells view that it is loading a request
     */
    fun startLoading()

    /**
     * Tells view that it has finished loading a request
     */
    fun stopLoading()
}