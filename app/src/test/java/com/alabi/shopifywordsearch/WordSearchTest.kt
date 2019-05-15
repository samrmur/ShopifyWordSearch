package com.alabi.shopifywordsearch

import com.alabi.shopifywordsearch.util.search.WordSearch
import org.junit.Test

import java.util.ArrayList

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class WordSearchTest {
    @Test
    fun createWordSearch() {
        val list = ArrayList<String>()

        list.add("kotlin")
        list.add("swift")
        list.add("variable")
        list.add("java")
        list.add("objectivec")
        list.add("mobile")

        val search = WordSearch()
        val game = search.createGrid(15, list)

        for (array in game.iterator()) {
            for (element in array)
                System.out.print("$element ")
            System.out.println()
        }

        assert(true)
    }
}
