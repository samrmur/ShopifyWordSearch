package com.alabi.shopifywordsearch.game.presentation

import com.alabi.shopifywordsearch.util.search.Word
import com.alabi.shopifywordsearch.util.search.WordSearch
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * Presenter used in a game view that creates a game and finds words by position
 * @author Samer Alabi
 */
class GamePresenter @Inject constructor(
    private val view: GameView,
    private val search: WordSearch
): CoroutineScope {
    private val job: Job = Job()

    override val coroutineContext: CoroutineContext get() = job + Dispatchers.Main

    /**
     * Creates an instance of a word search game
     * @param size size of the word search game
     * @param words list of words to place on the game board
     */
    fun createGame(size: Int, words: MutableList<String>) {
        // Tell view that the game is being created
        view.startLoading()

        // Launch coroutine to create game
        this.launch {
            // Create and send game
            val gameAndWords = search.createGrid(size, words, 100)
            view.onGameCreated(gameAndWords.first, gameAndWords.second)

            // Tell view that the game has been created
            view.stopLoading()
        }
    }

    fun findWordByPosition(words: ArrayList<Word>, startRow: Int, startColumn: Int, endRow: Int, endColumn: Int) {
        // Launch coroutine to find word
        this.launch {
            words.find { word ->
                !word.found.get() && (checkPositions(word, startRow, startColumn, endRow, endColumn) || checkPositions(word, endRow, endColumn, startRow, startColumn))
            }?.let { foundWord ->
                view.onWordFound(foundWord)
            }
        }
    }

    fun cancel() {
        coroutineContext.cancel()
    }

    private fun checkPositions(word: Word, startRow: Int, startColumn: Int, endRow: Int, endColumn: Int): Boolean {
        return word.start.first == startColumn && word.start.second == startRow && word.end.first == endColumn && word.end.second == endRow
    }
}