package com.example.roomwords.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomwords.MainActivity
import com.example.roomwords.R
import com.example.roomwords.model.WordEntity
import kotlinx.android.synthetic.main.layout_fragment_display.*
import kotlinx.android.synthetic.main.layout_fragment_display.view.*

class FragmentDisplay: Fragment() {

    var listener: IView? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //if(context instanceOf MainActivity)
        if(context is MainActivity)
            listener = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view= inflater.inflate(
            R.layout.layout_fragment_display,
            container,
            false)

        view.foab.setOnClickListener {
            listener?.navigateCreateFragment()
        }
        return view
    }

    fun displayData(dataSet: List<WordEntity>) {
        Log.d("FragmentDisplay", "displayData ${dataSet.size}")
        recycler_view.layoutManager = LinearLayoutManager(activity)
        val adapter= WordAdapter()
        recycler_view.adapter = adapter
        adapter.dataSet = dataSet
    }

    companion object{
        fun newInstance(): FragmentDisplay{
            return FragmentDisplay()
        }
    }
}