package com.alabi.shopifywordsearch.util.search

import java.util.Random

/**
 * Simple enum class to identify direction and generate random direction
 * @author Samer Alabi
 */
enum class Direction {
    UP, DOWN, LEFT, RIGHT, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT;

    companion object {
        /**
         * Generates a random direction
         * @return Direction
         */
        fun getRandomDirection(): Direction {
            // Get directions
            val random = Random()
            val directions = values()

            // Return random direction
            return directions[random.nextInt(directions.size)]
        }
    }
}