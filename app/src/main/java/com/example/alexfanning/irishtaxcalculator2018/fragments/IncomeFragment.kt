package com.example.alexfanning.irishtaxcalculator2018.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.AndroidException
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText

import com.example.alexfanning.irishtaxcalculator2018.R


/**
 * A simple [Fragment] subclass.
 */
class IncomeFragment : Fragment() {

    private lateinit var etAdvancedGrossSal : EditText
    private lateinit var chkBoxPension: CheckBox
    private lateinit var etAdvancedPension: EditText
    private lateinit var etAdvancedSpouseSal: EditText
    private lateinit var chkAdvancedCarer: CheckBox
    private lateinit var etCarerSalary: EditText


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater!!.inflate(R.layout.fragment_income, container, false)
        findViews(rootView)
        return rootView
    }

    private fun findViews(rootView: View?){
        etAdvancedGrossSal = rootView!!.findViewById(R.id.advanced_et_income)
        chkBoxPension = rootView.findViewById(R.id.advanced_chk_box_pension)
        etAdvancedPension = rootView.findViewById(R.id.advanced_et_pension)
        etAdvancedSpouseSal = rootView.findViewById(R.id.advanced_et_spouse_income)
        chkAdvancedCarer = rootView.findViewById(R.id.advanced_chk_box_carer)
        etCarerSalary = rootView.findViewById(R.id.advanced_et_carer_pay)

    }

}
