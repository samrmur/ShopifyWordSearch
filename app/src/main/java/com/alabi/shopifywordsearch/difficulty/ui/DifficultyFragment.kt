package com.alabi.shopifywordsearch.difficulty.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alabi.shopifywordsearch.R
import com.alabi.shopifywordsearch.difficulty.presentation.DifficultyPresenter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_difficulty.*
import javax.inject.Inject

/**
 * Fragment that allows a user to choose the difficulty of the word search game
 * they would like to play
 * @author Samer Alabi
 */
class DifficultyFragment: Fragment(), View.OnClickListener {
    @Inject
    lateinit var presenter: DifficultyPresenter

    companion object {
        const val TAG = "DIFFICULTY_FRAGMENT"
        fun newInstance(): DifficultyFragment = DifficultyFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_difficulty, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtons()
    }

    override fun onClick(view: View) {
        // Go to game based on selected difficulty
        when (view.id) {
            R.id.btn_easy -> presenter.toEasyGame()
            R.id.btn_medium -> presenter.toMediumGame()
            R.id.btn_hard -> presenter.toHardGame()
        }
    }

    /**
     * Setups UI buttons
     */
    private fun setupButtons() {
        btn_easy.setOnClickListener(this)
        btn_medium.setOnClickListener(this)
        btn_hard.setOnClickListener(this)
    }
}