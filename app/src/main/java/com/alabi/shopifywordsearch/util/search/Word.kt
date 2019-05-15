package com.alabi.shopifywordsearch.util.search

import java.util.concurrent.atomic.AtomicBoolean

/**
 * Simple data structure for holding a word and its location points
 * @author Samer Alabi
 */
data class Word constructor(
    val word: String,
    val start: Pair<Int, Int>,
    val end: Pair<Int, Int>,
    val found: AtomicBoolean
)