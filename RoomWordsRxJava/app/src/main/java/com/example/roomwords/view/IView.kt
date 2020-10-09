package com.example.roomwords.view

import com.example.roomwords.model.WordEntity

interface IView {
    //F.Display
    fun getAllWords()
    fun displayData(dataSet: List<WordEntity>)

    //F.Create
    fun saveWord(word: String)
    fun isWordValid(word: String): Boolean

    fun initFragments()
    fun bindPresenter()
    fun navigateCreateFragment()
}