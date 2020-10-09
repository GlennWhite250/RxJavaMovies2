package com.example.fragmenttest

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.widget.Toast

class MainActivity : AppCompatActivity(), Listener
{
    val connectionManager: ConnectionManager by lazy { ConnectionManager(this) }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun inflateFragmentTwo()
    {
        /**
        val fragment = FragmentTwo.newInstance()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_two_container, fragment)
        fragmentTransaction.commitNow()
        supportFragmentManager.executePendingTransactions()
        fragment.passData()
        **/

        val fragmentTwo = FragmentTwo.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_two_container, fragmentTwo).commitNow()
        supportFragmentManager.executePendingTransactions()

        val readableDB = connectionManager.readableDatabase
        val cursor =
        readableDB.query(InputTable.TABLE_NAME, arrayOf(InputTable.INPUT_TABLE_INPUT), null, null, null, null, "${InputTable.INPUT_TABLE_INPUT} DESC")


        var listOfData = mutableListOf<String>()
        with(cursor){
            while (moveToNext())
            {
                var input = getString(getColumnIndexOrThrow(InputTable.INPUT_TABLE_INPUT))
                listOfData.add(input)
            }
        }
        fragmentTwo.passData(listOfData)
    }

    override fun writeData(input: String)
    {
        val writableDB = connectionManager.writableDatabase
        /**
         * 2 ways to store data
         * Java way / Android way
         */

        val contentValues = ContentValues()
        contentValues.put(InputTable.INPUT_TABLE_INPUT, input)
        if(writableDB.insert(InputTable.TABLE_NAME, "", contentValues) < 0)
        {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }

        inflateFragmentTwo()
    }
}