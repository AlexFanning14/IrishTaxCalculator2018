package com.example.alexfanning.irishtaxcalculator2018.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.alexfanning.irishtaxcalculator2018.R


/**
 * A simple [Fragment] subclass.
 */
class ResultsFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater!!.inflate(R.layout.fragment_results, container, false)
        findViews(rootView)
        return rootView
    }


    private fun findViews(rootView: View?){

    }
}
