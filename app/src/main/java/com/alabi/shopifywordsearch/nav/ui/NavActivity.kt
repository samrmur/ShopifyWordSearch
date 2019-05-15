package com.alabi.shopifywordsearch.nav.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.alabi.shopifywordsearch.R
import com.alabi.shopifywordsearch.nav.presentation.NavPresenter
import com.alabi.shopifywordsearch.util.extensions.fragmentNotAttached
import com.alabi.shopifywordsearch.util.search.Word
import com.alabi.shopifywordsearch.util.search.WordSearch
import java.util.ArrayList
import com.alabi.shopifywordsearch.util.views.WordSearchTextView
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Activity used for navigating across a set of fragments
 * @author Samer Alabi
 */
class NavActivity: AppCompatActivity(), HasSupportFragmentInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var presenter: NavPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        if (fragmentNotAttached())
            presenter.toWelcome()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector
}