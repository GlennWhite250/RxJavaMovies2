package com.example.fragmenttest

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

/**
 * Step 1: Step up subclass SQLiteOpenHelper
 * Step 2: Define the operation (Read/Write)
 *      WritableDatabase
 *      ReadableDatabase
 */

class ConnectionManager(context: Context): SQLiteOpenHelper(context, "dbName", null, 1)
{
    override fun onCreate(p0: SQLiteDatabase?)
    {
        p0?.execSQL("CREATE TABLE IF NOT EXISTS  ${InputTable.TABLE_NAME}("+"${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "${InputTable.INPUT_TABLE_INPUT} VARCHAR(255)" + ")")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int)
    {
        p0?.execSQL("DROP TABLE IF EXISTS ${InputTable.TABLE_NAME}")
    }
}