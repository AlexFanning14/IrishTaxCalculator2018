package com.example.alexfanning.irishtaxcalculator2018

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var grossIncomeTxt: EditText
    private lateinit var calculateButton: Button
    private lateinit var tvTax : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViews()

    }


    private fun findViews(){
        grossIncomeTxt = findViewById(R.id.tv_salary)
        calculateButton = findViewById(R.id.btn_calculate)
        tvTax = findViewById(R.id.tv_tax)
        calculateButton.setOnClickListener { calculateTax()}

    }

    private fun calculateTax(){
        val grossIncome : Int = grossIncomeTxt.text.toString().toInt()

        val calculate = Calculation(grossIncome,MaritialStatus.SINGLE)
        calculate.calculateTotalTax()
        tvTax.setText(calculate.taxToPay.toString())





    }






}
