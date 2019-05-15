package com.alabi.shopifywordsearch.util.extensions

import java.util.Random

/**
 * Gets a random element
 * @return randomly selected element
 */
fun <T> List<T>.getRandomElement(): T {
    // Return random element
    val random = Random()
    return this[random.nextInt(size)]
}