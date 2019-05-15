package com.alabi.shopifywordsearch.util.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.alabi.shopifywordsearch.R

/**
 * Attaches a fragment to a activity and adds it to the fragment back stack
 * @param tag identifier for the fragment
 * @param layoutRes layout to attach fragment to
 * @param fragment function that creates a fragment
 */
fun AppCompatActivity.addFragmentToBackStack(tag: String?, layoutRes: Int, fragment: () -> Fragment) {
    supportFragmentManager.beginTransaction()
        .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
        .replace(layoutRes, fragment.invoke(), tag)
        .addToBackStack(tag)
        .commit()
}

/**
 * Attaches a fragment to a activity
 * @param tag identifier for the fragment
 * @param layoutRes layout to attach fragment to
 * @param fragment function that creates a fragment
 */
fun AppCompatActivity.addFragment(tag: String?, layoutRes: Int, fragment: () -> Fragment) {
    supportFragmentManager.beginTransaction()
        .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
        .replace(layoutRes, fragment.invoke(), tag)
        .commit()
}

fun AppCompatActivity.fragmentNotAttached() = supportFragmentManager.fragments.size == 0