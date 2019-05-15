package com.alabi.shopifywordsearch.util.search

import android.content.res.Resources
import com.alabi.shopifywordsearch.R

/**
 * Simple difficulty enum to identify difficulty of level
 * @author Samer Alabi
 */
enum class Difficulty {
    EASY, MEDIUM, HARD;

    companion object {
        /**
         * Returns the grid size based on a given difficulty
         * @param diff difficulty the word search is being played at
         * @return size of grid
         */
        fun getGridSize(diff: Difficulty): Int {
            return when (diff) {
                EASY -> 10
                MEDIUM -> 12
                HARD -> 15
            }
        }

        /**
         * Returns the word set for a given difficulty
         * @param diff difficulty the word search is being played at
         * @param resources set of resources to retrieve the words from
         * @return mutable list containing the words
         */
        fun getWordSet(diff: Difficulty, resources: Resources): MutableList<String> {
            // Get array id
            val arrayId = when (diff) {
                EASY -> R.array.easy
                MEDIUM -> R.array.medium
                HARD -> R.array.hard
            }

            // Return mutable list
            return resources.getStringArray(arrayId).toMutableList()
        }

        /**
         * Returns a difficulty enum from a given string if valid
         * @param str is the string representing the difficulty
         * @return the difficulty is found within the given string
         */
        fun fromString(str: String): Difficulty? {
            return when (str.toUpperCase()) {
                "EASY" -> EASY
                "MEDIUM" -> MEDIUM
                "HARD" -> HARD
                else -> null
            }
        }

        /**
         * Returns a string from a given enum
         * @param diff difficulty the word search is being played at
         * @return a string representing the difficulty
         */
        fun toString(diff: Difficulty): String {
            return when (diff) {
                EASY -> "EASY"
                MEDIUM -> "MEDIUM"
                HARD -> "HARD"
            }
        }
    }
}