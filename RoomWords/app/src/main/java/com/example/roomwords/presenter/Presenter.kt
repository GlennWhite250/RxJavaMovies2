package com.example.roomwords.presenter

import android.content.Context
import android.util.Log
import com.example.roomwords.RoomApplication
import com.example.roomwords.model.WordDao
import com.example.roomwords.model.WordEntity
import com.example.roomwords.model.WordRoomDB
import com.example.roomwords.view.IView

class Presenter {
    var view: IView? = null



    fun onBind(view: IView) {
        this.view = view
    }

    fun queryDB() {
        val dao: WordDao? =
            RoomApplication.roomContext?.let {
                WordRoomDB.getInstance(it).dao()
            }
        Thread(Runnable {

            val dataSet = dao?.getAllWords()
            dataSet?.let {
                view?.displayData(it)
            }

        })
    }

    fun insertWord(word: String) {
        //Log.d("Presenter", "insertWord "+ (dao!=null).toString())
        val dao: WordDao? =
            RoomApplication.roomContext?.let {
                WordRoomDB.getInstance(it).dao()
            }
        val wordEntity = WordEntity(word=word)

        Thread(Runnable {
            dao?.insertWord(wordEntity)
        })
    }

    fun isWordValid(word: String): Boolean {
        return word.isNotEmpty()
    }
}