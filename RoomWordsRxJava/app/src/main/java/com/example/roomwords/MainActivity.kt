package com.example.roomwords

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.roomwords.model.WordEntity
import com.example.roomwords.presenter.Presenter
import com.example.roomwords.view.FragmentCreate
import com.example.roomwords.view.FragmentDisplay
import com.example.roomwords.view.IView

class MainActivity : AppCompatActivity(), IView {

    lateinit var presenter: Presenter
    val fragmentCreate = FragmentCreate.newInstance()
    val fragmentDisplay = FragmentDisplay.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)
        bindPresenter()
        initFragments()
    }

    override fun getAllWords() {
        presenter.queryDB()
    }

    override fun displayData(dataSet: List<WordEntity>) {
        Log.d("MainActivity", "displayData ${dataSet.size}")
        fragmentDisplay.displayData(dataSet)
    }

    override fun saveWord(word: String) {
        presenter.insertWord(word)
    }

    override fun isWordValid(word: String): Boolean{
        return presenter.isWordValid(word)
    }

    override fun initFragments() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,
            fragmentDisplay)
            .addToBackStack(null)
            .commit()

        getAllWords()
    }

    override fun bindPresenter() {
        presenter = Presenter()
        presenter.onBind(this)
    }

    override fun navigateCreateFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,
            fragmentCreate)
            .addToBackStack(null)
            .commit()
    }
}