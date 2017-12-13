package com.example.alexfanning.irishtaxcalculator2018.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.alexfanning.irishtaxcalculator2018.R

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        this.supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
}
