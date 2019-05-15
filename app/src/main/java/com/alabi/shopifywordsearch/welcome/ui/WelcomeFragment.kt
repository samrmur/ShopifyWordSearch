package com.alabi.shopifywordsearch.welcome.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alabi.shopifywordsearch.R
import com.alabi.shopifywordsearch.welcome.presentation.WelcomePresenter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_welcome.*
import javax.inject.Inject

/**
 * First fragment that acts as a welcome screen for the user
 * @author Samer Alabi
 */
class WelcomeFragment: Fragment(), View.OnClickListener {
    @Inject
    lateinit var presenter: WelcomePresenter

    companion object {
        const val TAG = "WELCOME_FRAGMENT"
        fun newInstance(): WelcomeFragment = WelcomeFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_welcome, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        play_button.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        presenter.toDifficulty()
    }
}