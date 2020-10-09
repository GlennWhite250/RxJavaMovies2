package com.example.roomwords.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WordDao {
    @Query("SELECT * FROM word_table")
    fun getAllWords(): List<WordEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertWord(singleWord: WordEntity)

    @Query("DELETE FROM word_table")
    fun deleteAll()
}