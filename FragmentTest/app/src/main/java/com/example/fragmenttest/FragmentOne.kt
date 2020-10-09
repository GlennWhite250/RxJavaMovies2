package com.example.fragmenttest

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_one_layout.view.*

//This class inharets from the Fragment Class.
class FragmentOne: Fragment()
{
    lateinit var etInput: EditText
    lateinit var btnSave: Button
    lateinit var listener: Listener

    //the onCreateView was generated code, mostly
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         super.onCreateView(inflater, container, savedInstanceState)
        val viewFragment = inflater.inflate(R.layout.fragment_one_layout, container, false)

        //Java idea
        etInput = viewFragment.findViewById(R.id.tv_message)
        btnSave = viewFragment.findViewById(R.id.btn_send_message)

        //Kotlin Version using synthic views
        val ets = viewFragment.tv_message
        val btnS = viewFragment.btn_send_message

        btnS.setOnClickListener {
            //todo create reference to the
            //todo Activity Listener
            listener.writeData(ets.text.toString())
        }

        return viewFragment
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as Listener
    }

}