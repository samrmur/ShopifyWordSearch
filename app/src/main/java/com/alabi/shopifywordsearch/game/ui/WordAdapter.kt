package com.alabi.shopifywordsearch.game.ui

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.alabi.shopifywordsearch.R
import com.alabi.shopifywordsearch.util.search.Word
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import kotlinx.android.synthetic.main.item_word.view.*

/**
 * Simple adapter for displaying words on a listable view
 * @author Samer Alabi
 */
class WordAdapter(private val context: Context?): BaseAdapter() {
    private val words: ArrayList<Word> = ArrayList()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // If view has not been created, inflate view
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_word, parent, false)

        // Get word and view
        val word = getItem(position)
        val textView = view.word_text

        // If word has been found, cross it out
        if (word.found.get()) {
            textView.typeface = Typeface.DEFAULT
            textView.paintFlags = textView.paintFlags or STRIKE_THRU_TEXT_FLAG
        }

        // Set text
        textView.text = word.word

        // Return view
        return view
    }

    override fun getItem(position: Int): Word = words[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = words.size

    /**
     * Adds all words to adapter
     * @param words list of words
     */
    fun addAll(words: ArrayList<Word>) {
        this.words.addAll(words)
        notifyDataSetChanged()
    }

    /**
     * Updates a word by setting it to true
     * @param word word object
     */
    fun foundWord(word: Word) {
        words.find { currentWord ->
            currentWord.word == word.word
        }?.let { foundWord ->
            foundWord.found.set(true)
            notifyDataSetChanged()
        }
    }
}