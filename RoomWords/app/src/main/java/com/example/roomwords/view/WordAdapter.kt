package com.example.roomwords.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomwords.R
import com.example.roomwords.model.WordEntity
import kotlinx.android.synthetic.main.item_layout.view.*

class WordAdapter: RecyclerView.Adapter<WordAdapter.WordViewHolder>() {

    var dataSet: List<WordEntity> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }


    class WordViewHolder(wordItem: View): RecyclerView.ViewHolder(wordItem){
        val tvWord: TextView = wordItem.tv_word_item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        WordViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.item_layout,
            parent,
            false
        ))

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.tvWord.text = dataSet[position].word
    }
}