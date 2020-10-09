package com.example.roomwords.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.roomwords.R

@Database(
    entities = [WordEntity::class],
    version = 3, exportSchema = true
)
abstract class WordRoomDB : RoomDatabase() {
    abstract fun dao(): WordDao

    companion object {
        @Volatile
        var INSTANCE: WordRoomDB? = null

        fun getInstance(context: Context):
                WordRoomDB {
            val tempInstance = INSTANCE
            if (tempInstance != null)
                return tempInstance
            synchronized(this) {
                val instance = Room
                    .databaseBuilder(
                        context,
                        WordRoomDB::class.java,
                        context.getString(R.string.db_name)
                    )
                    //.addCallback(RoomCallback())
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class RoomCallback() :
        RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { populateDB(it.dao()) }
        }

        private fun populateDB(dao: WordDao) {
            Thread(Runnable {
                dao.deleteAll()
                var wordEntity = WordEntity(word = "HOLA")
                dao.insertWord(wordEntity)
                wordEntity = WordEntity(word = "QUE")
                dao.insertWord(wordEntity)
                wordEntity = WordEntity(word = "BYE")
                dao.insertWord(wordEntity)
            })
        }
    }
}