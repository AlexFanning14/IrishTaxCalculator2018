package com.example.alexfanning.irishtaxcalculator2018.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.alexfanning.irishtaxcalculator2018.Calculation

import com.example.alexfanning.irishtaxcalculator2018.R
import com.example.alexfanning.irishtaxcalculator2018.adapters.SpinnerHintAdapter


class StatusFragment : Fragment() {

    private lateinit var spnWorker: Spinner
    private lateinit var spnMarital: Spinner
    private lateinit var spnAge: Spinner
    private lateinit var spnAgeSpouse: Spinner
    private lateinit var chkMedCard: CheckBox
    private lateinit var spnSpouseWorker: Spinner
    private lateinit var chkChildren: CheckBox
    private lateinit var fabNext : FloatingActionButton

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val rootView = inflater!!.inflate(R.layout.fragment_status, container, false)
        findViews(rootView)
        return rootView;
    }

    private fun findViews(rootView: View?) {
        spnWorker = rootView!!.findViewById(R.id.advanced_spinner_worker_status)
        spnMarital = rootView.findViewById(R.id.advanced_spinner_marital_status)
        spnAge = rootView.findViewById(R.id.advanced_spinner_age)
        spnAgeSpouse = rootView.findViewById(R.id.advanced_spinner_age_spouse)
        chkChildren = rootView.findViewById(R.id.advanced_chk_box_children)
        chkMedCard = rootView.findViewById(R.id.advanced_chk_box_med_card)
        spnSpouseWorker = rootView.findViewById(R.id.advanced_spinner_worker_status_spouse)
        fabNext = rootView.findViewById(R.id.fab_status_next)
        setUpSpinners()
    }

    private fun nextPage(){

    }






    override fun onAttach(context: Context?) {
        super.onAttach(context)

    }

    private fun setUpSpinners() {
        val wrkerAdapter = SpinnerHintAdapter(context, R.layout.custom_spinner_layout, resources.getStringArray(R.array.spinner_employment_array))
        spnWorker.adapter = wrkerAdapter
        spnWorker.setSelection(wrkerAdapter.count)

        val wrkerAdapterSpouse = SpinnerHintAdapter(context, R.layout.custom_spinner_layout, resources.getStringArray(R.array.spinner_employment_array_spouse))
        spnSpouseWorker.adapter = wrkerAdapterSpouse
        spnSpouseWorker.setSelection(wrkerAdapterSpouse.count)


        val marAdapter = SpinnerHintAdapter(context, R.layout.custom_spinner_layout, resources.getStringArray(R.array.spinner_marital_array))
        spnMarital.adapter = marAdapter
        spnMarital.setSelection(marAdapter.count)
        spnMarital.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                if (position == 5) {
                    spnSpouseWorker.visibility = Spinner.VISIBLE
                    spnAgeSpouse.visibility = Spinner.VISIBLE
                } else {
                    spnSpouseWorker.visibility = Spinner.GONE
                    spnAgeSpouse.visibility = Spinner.GONE
                }

            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // your code here
            }
        })


        val ageAdapter = SpinnerHintAdapter(context, R.layout.custom_spinner_layout, resources.getStringArray(R.array.spinner_age_array))
        spnAge.adapter = ageAdapter
        spnAge.setSelection(ageAdapter.count)

        val ageAdapterSpouse = SpinnerHintAdapter(context, R.layout.custom_spinner_layout, resources.getStringArray(R.array.spinner_age_array_spouse))
        spnAgeSpouse.adapter = ageAdapterSpouse
        spnAgeSpouse.setSelection(ageAdapterSpouse.count)


    }
}




