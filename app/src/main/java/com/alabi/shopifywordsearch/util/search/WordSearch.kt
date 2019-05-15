package com.alabi.shopifywordsearch.util.search

import com.alabi.shopifywordsearch.util.extensions.getRandomElement
import java.util.Random
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Word Search Generator
 * @author Samer Alabi
 */
class WordSearch {
    private val random = Random()
    private val source = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

    /**
     * Creates a Word Search Game even a game size and a list of words. (Remove words
     * from list. Any remaining words are words that could not be put into the current game)
     * @param size grid size (X * X)
     * @param words list of words to be put onto the grid
     * @param maxAttempts is the maximum number of attempts the algorithm should spend trying to put a word on the grid
     * @return pair with game and list of placed words
     */
    fun createGrid(size: Int, words: MutableList<String>, maxAttempts: Int): Pair<Array<CharArray>, ArrayList<Word>> {
        // Create board
        val game = Array(size) { CharArray(size) { '-' } }
        val placedWords = ArrayList<Word>()
        var attemptsRemaining = maxAttempts

        // Loop until all words have been placed or the max attempts fail
        while (words.isNotEmpty() && maxAttempts > 0) {
            // Get random word and direction
            val word: String = words.getRandomElement()
            val direction = Direction.getRandomDirection()

            // Ensure word is of good length
            if (word.length <= size) {
                var placed = false

                // Place word based on direction
                when (direction) {
                    Direction.UP -> {
                        val horizontalPos = random.nextInt(size)
                        val verticalPos = (size-1) - if (word.length == size) 0 else random.nextInt(size-word.length)
                        placed = place(word, placedWords, game, verticalPos, horizontalPos, -1, 0)
                    }
                    Direction.DOWN -> {
                        val horizontalPos = random.nextInt(size)
                        val verticalPos = if (word.length == size) 0 else random.nextInt(size-word.length)
                        placed = place(word, placedWords, game, verticalPos, horizontalPos, 1, 0)
                    }
                    Direction.LEFT -> {
                        val horizontalPos = if (word.length == size) 0 else random.nextInt(size-word.length)
                        val verticalPos = random.nextInt(size)
                        placed = place(word, placedWords, game, verticalPos, horizontalPos, 0, 1)
                    }
                    Direction.RIGHT -> {
                        val horizontalPos = (size-1) - if (word.length == size) 0 else random.nextInt(size-word.length)
                        val verticalPos = random.nextInt(size)
                        placed = place(word, placedWords, game, verticalPos, horizontalPos, 0, -1)
                    }
                    Direction.UP_LEFT -> {
                        val horizontalPos = if (word.length == size) 0 else random.nextInt(size-word.length)
                        val verticalPos = (size-1) - if (word.length == size) 0 else random.nextInt(size-word.length)
                        placed = place(word, placedWords, game, verticalPos, horizontalPos, -1, 1)
                    }
                    Direction.UP_RIGHT -> {
                        val horizontalPos = (size-1) - if (word.length == size) 0 else random.nextInt(size-word.length)
                        val verticalPos = (size-1) - if (word.length == size) 0 else random.nextInt(size-word.length)
                        placed = place(word, placedWords, game, verticalPos, horizontalPos, -1, -1)
                    }
                    Direction.DOWN_LEFT -> {
                        val horizontalPos = if (word.length == size) 0 else random.nextInt(size-word.length)
                        val verticalPos = if (word.length == size) 0 else random.nextInt(size-word.length)
                        placed = place(word, placedWords, game, verticalPos, horizontalPos, 1, 1)
                    }
                    Direction.DOWN_RIGHT -> {
                        val horizontalPos = (size-1) - if (word.length == size) 0 else random.nextInt(size-word.length)
                        val verticalPos = if (word.length == size) 0 else random.nextInt(size-word.length)
                        placed = place(word, placedWords, game, verticalPos, horizontalPos, 1, -1)
                    }
                }

                // Remove word if placed
                if (placed) words.remove(word) else attemptsRemaining--
            } else {
                words.remove(word)
            }
        }

        // Fill remaining slots
        for (array in game)
            for (i in 0 until array.size)
                if (array[i] == '-') array[i] = source[random.nextInt(source.length)]

        // Return game
        return Pair(game, placedWords)
    }

    /**
     * Places a word on a given game board based on the direction
     * @param word word to place on the board
     * @param placedWords list of placed words to add to
     * @param game game board as a 2D array
     * @param verticalPos starting vertical position (column) of the word
     * @param horizontalPos starting horizontal position (row) of the word
     * @param verticalCremeter value to increment or decrement vertically until word has been placed
     * @param horizontalCremeter value to increment or decrement horizontally until word has been placed
     * @return true if placed, false otherwise
     */
    private fun place(word: String, placedWords: ArrayList<Word>, game: Array<CharArray>, verticalPos: Int, horizontalPos: Int, verticalCremeter: Int, horizontalCremeter: Int): Boolean {
        // Create iterator
        var canBePlaced = true
        var iteratorVer = verticalPos
        var iteratorHor = horizontalPos

        // Check if the word can be placed
        for (i in 0 until word.length) {
            val element = game[iteratorVer][iteratorHor]

            // Increment iterators
            iteratorVer += verticalCremeter
            iteratorHor += horizontalCremeter

            // If it can't be placed, break the loop
            if (element != '-' && element != word[i]) {
                canBePlaced = false
                break
            }
        }

        // If can be placed, place the word and return true, else false
        return if (canBePlaced) {
            // Reset cremeters
            iteratorVer = verticalPos
            iteratorHor = horizontalPos

            // Place word
            for (i in 0 until word.length) {
                game[iteratorVer][iteratorHor] = word[i].toUpperCase()

                // If last index, add element to the word list
                if (i == word.length - 1) placedWords.add(Word(word, Pair(verticalPos, horizontalPos), Pair(iteratorVer, iteratorHor), AtomicBoolean(false)))

                // Increment iterators
                iteratorVer += verticalCremeter
                iteratorHor += horizontalCremeter
            }

            // Return true
            true
        } else {
            false
        }
    }
}