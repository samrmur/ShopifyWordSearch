package com.alabi.shopifywordsearch.game.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.doOnLayout
import androidx.fragment.app.Fragment
import com.alabi.shopifywordsearch.R
import com.alabi.shopifywordsearch.game.presentation.GamePresenter
import com.alabi.shopifywordsearch.game.presentation.GameView
import com.alabi.shopifywordsearch.util.search.Difficulty
import com.alabi.shopifywordsearch.util.search.Word
import com.alabi.shopifywordsearch.util.views.WordSearchTextView
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_game.*
import javax.inject.Inject
import kotlin.collections.ArrayList

/**
 * Fragment that represents the game
 * @author Samer Alabi
 */
class GameFragment: Fragment(), GameView {
    @Inject
    lateinit var presenter: GamePresenter

    lateinit var game: Array<CharArray>
    lateinit var words: ArrayList<Word>

    private var numFound: Int = -1

    companion object {
        // Fragment Keys
        const val TAG = "GAME_FRAGMENT"
        const val BUNDLE_DIFFICULTY = "BUNDLE_DIFFICULTY"

        /**
         * Creates a new instance of a game fragment
         * @param difficulty difficulty the game is being played at
         * @return new game fragment
         */
        fun newInstance(difficulty: Difficulty): GameFragment {
            // Create fragment
            val fragment = GameFragment()

            // Create bundle
            val bundle = Bundle()
            bundle.putString(BUNDLE_DIFFICULTY, difficulty.toString())
            fragment.arguments = bundle

            // Return new fragment with arguments
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_game, container, false)

    override fun onDestroy() {
        presenter.cancel()
        super.onDestroy()
    }

    override fun onGameCreated(game: Array<CharArray>, words: ArrayList<Word>) {
        // Store game
        this.game = game
        this.words = words

        // Setup grid if difficulty is available
        runIfDiffAvailable { diff ->
            setupViews(diff)
        }
    }

    override fun startLoading() {
        loading?.visibility = View.VISIBLE
        game_layout?.visibility = View.INVISIBLE
    }

    override fun stopLoading() {
        loading?.visibility = View.INVISIBLE
        game_layout?.visibility = View.VISIBLE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Create game if difficulty available
        runIfDiffAvailable { diff ->
            createGame(diff)
        }
    }

    override fun onWordFound(word: Word) {
        // Set word to found
        (words_grid.adapter as WordAdapter).foundWord(word)

        // Get points for word
        val start = word.start
        val end = word.end

        // Add line to grid
        game_grid.addPermanentLine(start.second, start.first, end.second, end.first)

        // Update title
        updateTitle()
    }

    /**
     * Checks for words after a user has pulled their finger off the screen after a gesture
     * @param words list of words
     */
    private fun checkForWords(words: ArrayList<Word>) {
        game_grid.doOnLayout {
            game_grid.setOnTouchFinished { startRow, startColumn, endRow, endColumn ->
                presenter.findWordByPosition(words, startRow, startColumn, endRow, endColumn)
            }
        }
    }

    /**
     * Creates a word search game
     * @param difficulty difficulty the game is being played at
     */
    private fun createGame(difficulty: Difficulty) {
        // Get game parameters
        val size = Difficulty.getGridSize(difficulty)
        val words = Difficulty.getWordSet(difficulty, resources)

        // Create game
        presenter.createGame(size, words)
    }

    /**
     * Runs a given function if difficulty was passed in as a parameter in the fragment
     * @param func function of arity 1
     */
    private fun runIfDiffAvailable(func: (diff: Difficulty) -> Unit) {
        arguments?.getString(BUNDLE_DIFFICULTY)?.let { str ->
            Difficulty.fromString(str)?.let { diff ->
                func(diff)
            }
        }
    }

    /**
     * Setups all the views for this fragment
     * @param difficulty difficulty the game is being played at
     */
    private fun setupViews(difficulty: Difficulty) {
        // Get size of board
        val size = Difficulty.getGridSize(difficulty)

        // Make square grid
        game_grid.columnCount = size
        game_grid.rowCount = size

        // Add grid array to visual layout
        for (i in 0 until size) {
            for (j in 0 until size) {
                val textView = WordSearchTextView(requireActivity())
                textView.text = game[i][j].toString()
                game_grid.addView(textView)
            }
        }

        // Create and initialize adapter
        val adapter = WordAdapter(context)
        words_grid.adapter = adapter
        adapter.addAll(words)

        // Update title
        updateTitle()

        // Check for words
        checkForWords(words)
    }

    /**
     * Updates the title that indicates the amount of words found
     */
    private fun updateTitle() {
        // Display text
        num_found.text = resources.getString(R.string.game_title, ++numFound, words.size)

        // Display toast if all words found
        if (numFound == words.size)
            Toast.makeText(context, resources.getText(R.string.congratulations), Toast.LENGTH_LONG).show()
    }
}