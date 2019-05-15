package com.alabi.shopifywordsearch.core.module

import com.alabi.shopifywordsearch.util.search.WordSearch
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class WordSearchModule {
    @Singleton
    @Provides
    fun providesWordSearch(): WordSearch = WordSearch()
}