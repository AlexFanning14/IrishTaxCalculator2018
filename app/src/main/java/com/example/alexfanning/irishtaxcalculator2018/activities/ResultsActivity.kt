package com.example.alexfanning.irishtaxcalculator2018.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.alexfanning.irishtaxcalculator2018.Calculation
import com.example.alexfanning.irishtaxcalculator2018.R
import com.example.alexfanning.irishtaxcalculator2018.formatResult

class ResultsActivity : AppCompatActivity() {


    private lateinit var tv_income : TextView
    private lateinit var tv_income_total : TextView
    private lateinit var tv_lower_rate : TextView
    private lateinit var tv_higher_rate : TextView
    private lateinit var tv_tax_creds : TextView
    private lateinit var tv_net_tax : TextView
    private lateinit var tv_prsi : TextView
    private lateinit var tv_usc : TextView
    private lateinit var tv_total_tax : TextView
    private lateinit var tv_annual : TextView
    private lateinit var tv_monthly : TextView
    private lateinit var tv_weekly : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        this.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        findViews()
        val c = intent.getParcelableExtra<Calculation>("Calc")
        displayResults(c)
    }

    private fun findViews(){
        tv_income = findViewById<TextView>(R.id.tv_income_results)
        tv_income_total = findViewById(R.id.tv_income_total_results)
        tv_lower_rate = findViewById(R.id.tv_lower_rate_results)
        tv_higher_rate = findViewById(R.id.tv_higher_rate_results)
        tv_tax_creds = findViewById(R.id.tv_tax_creds_results)
        tv_net_tax = findViewById(R.id.tv_net_tax_results)
        tv_prsi = findViewById(R.id.tv_prsi_results)
        tv_usc = findViewById(R.id.tv_usc_results)
        tv_total_tax = findViewById(R.id.tv_total_tax_results)
        tv_annual = findViewById(R.id.tv_annual_net_results)
        tv_monthly = findViewById(R.id.tv_monthly_net_results)
        tv_weekly = findViewById(R.id.tv_weekly_net_results)
    }

    private fun displayResults(c : Calculation){
        tv_income.text = c.grossPay.toString().formatResult()
        tv_income_total.text = c.grossPay.toString().formatResult()
        tv_lower_rate.text = c.lowerTax.toInt().toString().formatResult()
        tv_higher_rate.text = c.higherTax.toInt().toString().formatResult()
        tv_tax_creds.text = "(" +c.taxCreditTotal.toString().formatResult() + ")"
        tv_net_tax.text = c.incomeTax.toString().formatResult()
        tv_prsi.text = c.PRSI.toString().formatResult()
        tv_usc.text = c.USC.toString().formatResult()
        tv_total_tax.text = c.totalTax.toString().formatResult()
        tv_annual.text = c.annualNet.toString().formatResult()
        tv_monthly.text = c.monthlyNet.toString().formatResult()
        tv_weekly.text = c.weeklyNet.toString().formatResult()
    }


}
