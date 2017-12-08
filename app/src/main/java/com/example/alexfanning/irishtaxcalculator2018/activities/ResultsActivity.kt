package com.example.alexfanning.irishtaxcalculator2018.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.alexfanning.irishtaxcalculator2018.Calculation
import com.example.alexfanning.irishtaxcalculator2018.R

class ResultsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        this.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val c = intent.getParcelableExtra<Calculation>("Calc")
        val b = intent.extras
        val a = c.PRSI

    }
}
