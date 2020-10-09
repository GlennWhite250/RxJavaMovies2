package com.example.roomwords.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Maybe


@Dao
interface  WordDao {
    /**
     * In a RX Java implementation, you can change your
     * functions to return an Observable or a Single or Completable
     * what ever make sense
     */
    @Query("SELECT * FROM word_table")
    fun getAllWords(): Maybe<List<WordEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertWord(singleWord: WordEntity): Completable

    @Query("DELETE FROM word_table")
    fun deleteAll(): Completable
}