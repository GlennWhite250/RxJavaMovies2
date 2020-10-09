package com.example.roomwords.presenter

import android.util.Log
import com.example.roomwords.RoomApplication
import com.example.roomwords.model.WordDao
import com.example.roomwords.model.WordEntity
import com.example.roomwords.model.WordRoomDB
import com.example.roomwords.view.IView
import io.reactivex.CompletableObserver
import io.reactivex.MaybeObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class Presenter {
    val TAG = "Presenter"
    var view: IView? = null

    fun onBind(view: IView) {
        this.view = view
    }

    fun queryDB() {
        val dao: WordDao? =
            RoomApplication.roomContext?.let {
                WordRoomDB.getInstance(it).dao()
            }
        dao?.getAllWords()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : MaybeObserver<List<WordEntity>> {
                override fun onSubscribe(d: Disposable){}

                override fun onSuccess(t: List<WordEntity>){
                    Log.d(TAG, "onSuccess ")
                    view!!.displayData(t)
                }

                override fun onError(e: Throwable){
                    Log.e(TAG, "onError")
                }

                override fun onComplete(){}
            })
    }

    fun insertWord(word: String) {
        val dao: WordDao? =
            RoomApplication.roomContext?.let {
                WordRoomDB.getInstance(it).dao()
            }
        val wordEntity = WordEntity(word=word)
//
//        Thread(Runnable {
//            dao?.insertWord(wordEntity)
//        })
        dao?.insertWord(wordEntity)
            ?.subscribeOn(Schedulers.io())
            ?.subscribe(object: CompletableObserver {
                override fun onSubscribe(d: Disposable){}

                override fun onComplete(){
                    Log.d(TAG, "insertWord onComplete")
                    view?.getAllWords()
                }

                override fun onError(e: Throwable){
                    Log.e(TAG, "onError "+e.message)
                }
            })
    }

    fun isWordValid(word: String): Boolean {
        return word.isNotEmpty()
    }
}