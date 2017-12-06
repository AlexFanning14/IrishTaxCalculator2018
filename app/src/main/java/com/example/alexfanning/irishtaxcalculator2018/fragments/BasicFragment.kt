package com.example.alexfanning.irishtaxcalculator2018.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import com.example.alexfanning.irishtaxcalculator2018.adapters.SpinnerHintAdapter
import java.util.*
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import com.example.alexfanning.irishtaxcalculator2018.*
import com.example.alexfanning.irishtaxcalculator2018.activities.ResultsActivity


/**
 * A simple [Fragment] subclass.
 */
class BasicFragment : Fragment() {

    private lateinit var etGrossSal: EditText
    private lateinit var spnWorker: Spinner
    private lateinit var spnMarital: Spinner
    private lateinit var spnAge: Spinner
    private lateinit var spnChild: Spinner
    private lateinit var chkMedCard: CheckBox
    private lateinit var btnCalculate: Button
    private lateinit var spnSpouseWorker : Spinner
    private lateinit var etGrossSalSpouse : EditText



    //Test
    private lateinit var etResult: TextView

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater!!.inflate(R.layout.fragment_basic, container, false)
        findViews(rootView)
        setUpSpinners()
        return rootView;

    }

    private fun findViews(rootView: View?) {
        //etResult = rootView!!.findViewById<TextView>(R.id.result)

        etGrossSal = rootView!!.findViewById<EditText>(R.id.et_gross_sal)
        etGrossSalSpouse = rootView.findViewById<EditText>(R.id.et_gross_sal_spouse)
        spnWorker = rootView.findViewById<Spinner>(R.id.basic_spinner_worker_status)
        spnSpouseWorker = rootView.findViewById<Spinner>(R.id.basic_spinner_worker_status_spouse)
        spnMarital = rootView.findViewById<Spinner>(R.id.basic_spinner_marital_status)



        spnAge = rootView.findViewById<Spinner>(R.id.basic_spinner_age)
        spnChild = rootView.findViewById<Spinner>(R.id.basic_spinner_children)
        chkMedCard = rootView.findViewById<CheckBox>(R.id.basic_chk_box_med_card)
        btnCalculate = rootView.findViewById<Button>(R.id.basic_btn_calculate)

        btnCalculate.setOnClickListener { completeCalculation() }


    }

    private fun completeCalculation(){
        startActivity(Intent(context,ResultsActivity::class.java))
//        val grossSal = etGrossSal.text.toString().toInt()
//        val employemntStatus = EmploymentStatus.from(spnWorker.selectedItemPosition)
//        val maritalStatus = MaritalStatus.from(spnMarital.selectedItemPosition)
//        val ageStatus = AgeStatus.from(spnAge.selectedItemPosition)
//        val childStatus = ChildStatus.from(spnChild.selectedItemPosition)
//        val hasMedicalCard = chkMedCard.isChecked


//        val c : Calculation
//        if (maritalStatus == MaritalStatus.MARRIED_TWO_WORKING){
//           val spouseEmplyStatus = EmploymentStatus.from(spnSpouseWorker.selectedItemPosition)
//            val spouseSal = etGrossSalSpouse.text.toString().toInt()
//            c = Calculation(grossSal,employemntStatus,maritalStatus,ageStatus,childStatus,hasMedicalCard,spouseEmplyStatus,spouseSal)
//        }else
//            c = Calculation(grossSal,employemntStatus,maritalStatus,ageStatus,childStatus,hasMedicalCard)
//        c.calculateTotalTax()

    }





    private fun setUpSpinners() {

        val wrkerAdapter = SpinnerHintAdapter(context, R.layout.support_simple_spinner_dropdown_item, resources.getStringArray(R.array.spinner_employment_array))
        spnWorker.adapter = wrkerAdapter
        spnWorker.setSelection(wrkerAdapter.count)

        val wrkerAdapterSpouse = SpinnerHintAdapter(context, R.layout.support_simple_spinner_dropdown_item, resources.getStringArray(R.array.spinner_employment_array_spouse))
        spnSpouseWorker.adapter = wrkerAdapterSpouse
        spnWorker.setSelection(wrkerAdapterSpouse.count)


        val marAdapter = SpinnerHintAdapter(context, R.layout.support_simple_spinner_dropdown_item, resources.getStringArray(R.array.spinner_marital_array))
        spnMarital.adapter = marAdapter
        spnMarital.setSelection(marAdapter.count)
        spnMarital.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                if (position == 3){
                    Toast.makeText(context, "Item Selected Number: " + position, Toast.LENGTH_SHORT).show()
                    spnSpouseWorker.visibility = Spinner.VISIBLE
                    etGrossSalSpouse.visibility = EditText.VISIBLE
                }
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // your code here
            }

        })


        val ageAdapter = SpinnerHintAdapter(context, R.layout.support_simple_spinner_dropdown_item, resources.getStringArray(R.array.spinner_age_array))
        spnAge.adapter = ageAdapter
        spnAge.setSelection(ageAdapter.count)

        val childAdapter = SpinnerHintAdapter(context, R.layout.support_simple_spinner_dropdown_item, resources.getStringArray(R.array.spinner_children_array))
        spnChild.adapter = childAdapter
        spnChild.setSelection(childAdapter.count)

    }


}
