package com.example.alexfanning.irishtaxcalculator2018.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.alexfanning.irishtaxcalculator2018.Calculation
import com.example.alexfanning.irishtaxcalculator2018.EmploymentStatus
import com.example.alexfanning.irishtaxcalculator2018.MaritalStatus

import com.example.alexfanning.irishtaxcalculator2018.R
import com.example.alexfanning.irishtaxcalculator2018.adapters.SpinnerHintAdapter
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class BasicFragment : Fragment() {

    private lateinit var etGrossSal : EditText
    private lateinit var spnWorker : Spinner
    private lateinit var spnMarital : Spinner
    private lateinit var spnAge : Spinner
    private lateinit var spnChild : Spinner
    private lateinit var chkMedCard : CheckBox
    private lateinit var btnCalculate : Button

    //Test
    private lateinit var etResult : TextView

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater!!.inflate(R.layout.fragment_basic, container, false)
        findViews(rootView)
        setUpSpinners()
        return rootView;

    }

    private fun findViews(rootView: View?){
        //etResult = rootView!!.findViewById<TextView>(R.id.result)

        etGrossSal = rootView!!.findViewById<EditText>(R.id.et_gross_sal)
        spnWorker = rootView.findViewById<Spinner>(R.id.basic_spinner_worker_status)
        spnMarital = rootView.findViewById<Spinner>(R.id.basic_spinner_marital_status)
        spnAge = rootView.findViewById<Spinner>(R.id.basic_spinner_age)
        spnChild = rootView.findViewById<Spinner>(R.id.basic_spinner_children)
        chkMedCard = rootView.findViewById<CheckBox>(R.id.basic_chk_box_med_card)
        btnCalculate = rootView.findViewById<Button>(R.id.basic_btn_calculate)
        btnCalculate.setOnClickListener {
            val a = etGrossSal.text.toString().toInt()
            val c = Calculation(a,MaritalStatus.SINGLE,EmploymentStatus.PAYE_WORKER)
            val a1 = c.calculateTotalTax()

            etResult.text = c.netIncome.toString()
        }
    }

    private fun setUpSpinners(){

        val wrkerAdapter  = SpinnerHintAdapter(context,R.layout.support_simple_spinner_dropdown_item,resources.getStringArray(R.array.spinner_employment_array))
        spnWorker.adapter = wrkerAdapter
        spnWorker.setSelection(wrkerAdapter.count)

        val marAdapter  = SpinnerHintAdapter(context,R.layout.support_simple_spinner_dropdown_item,resources.getStringArray(R.array.spinner_marital_array))
        spnMarital.adapter = marAdapter
        spnMarital.setSelection(marAdapter.count)

        val ageAdapter = SpinnerHintAdapter(context,R.layout.support_simple_spinner_dropdown_item,resources.getStringArray(R.array.spinner_age_array))
        spnAge.adapter = ageAdapter
        spnAge.setSelection(ageAdapter.count)

        val childAdapter = SpinnerHintAdapter(context,R.layout.support_simple_spinner_dropdown_item,resources.getStringArray(R.array.spinner_children_array))
        spnChild.adapter = childAdapter
        spnChild.setSelection(childAdapter.count)

    }



}
