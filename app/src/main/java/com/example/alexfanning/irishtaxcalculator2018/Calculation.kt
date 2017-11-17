package com.example.alexfanning.irishtaxcalculator2018

import android.support.annotation.IntegerRes
import android.util.Log
import java.util.*
import kotlin.collections.HashMap

/**
 * Created by alex.fanning on 16/11/2017.
 */
data class Calculation(val grossPay: Int,val marStatus: MaritalStatus, val emplStatus: EmploymentStatus) {

    var incomeTax : Int = 0
    var PRSI : Int = 0
    var USC: Int = 0
    var netIncome: Int = 0
    var taxCreditTotal : Int =0


    fun calculateTotalTax(): Int{
        generateTaxCredits()
        var incomeTaxVar : Float =0f

        if (grossPay > ConstantValues2018.STANDARD_RATE_TAX_BAND_SINGLE){
            val remainder = grossPay - ConstantValues2018.STANDARD_RATE_TAX_BAND_SINGLE
            val lowerTax = ConstantValues2018.STANDARD_RATE_TAX_BAND_SINGLE * ConstantValues2018.LOWER_RATE_PERCENT
            val higherTax = remainder * ConstantValues2018.HIGHER_RATE_PERCENT
            incomeTaxVar = lowerTax + higherTax
        }else{
            incomeTaxVar = grossPay * ConstantValues2018.LOWER_RATE_PERCENT
        }
        incomeTax = Math.round(incomeTaxVar)
        USC = calculateUSC()
        PRSI = calculatePRSI()

        incomeTax -= taxCreditTotal

        if (incomeTax < 0){
            incomeTax = 0
        }

        val totalTax = incomeTax + USC + PRSI


        netIncome = grossPay - totalTax

        return netIncome

    }

    private fun calculateUSC(): Int{
        if (grossPay < 13000){
            return 0
        }
        val keys : List<Long> = ConstantValues2018.USC_VALS.keys.toList()
        var totalUsc = 0f
        var i = 0
        while (i < keys.size){
            try {
                if (grossPay > keys[i] && grossPay > keys[i + 1]){
                    val valToTax = keys[i + 1] - keys[i]
                    val uscPC : Float? = ConstantValues2018.USC_VALS.get(keys[i])
                    totalUsc += (valToTax * uscPC as Float)
                }else if (grossPay > keys[i]){
                    val valToTax = grossPay - keys[i]
                    val uscPC : Float? = ConstantValues2018.USC_VALS.get(keys[i])
                    totalUsc += (valToTax * uscPC as Float)
                }
            }catch (ex: IndexOutOfBoundsException){
                val valToTax = grossPay - keys[i]
                val uscPC : Float? = ConstantValues2018.USC_VALS.get(keys[i])
                totalUsc += (valToTax * uscPC as Float)
            }

            i++
        }
        return Math.round(totalUsc)
    }


    private fun calculatePRSI():Int{

        if (grossPay <= ConstantValues2018.PRSI_LOWER_CUT_OFF_ANNUAL){
            return 0
        }else if (grossPay > ConstantValues2018.PRSI_LOWER_CUT_OFF_ANNUAL && grossPay < ConstantValues2018.PRSI_HIGHER_CUT_OFF_ANNUAL){
            val weeklyVal = grossPay.toFloat() / ConstantValues2018.WEEKS_IN_YEAR
            val weeklyDiff = (weeklyVal - ConstantValues2018.PRSI_LOWER_CUT_OFF_WEEKLY) / ConstantValues2018.PRSI_TAX_CRED_DIVISBLE_FACTOR
            val taxCred = ConstantValues2018.PRSI_TAX_CRED - weeklyDiff
            val basicPrsi = weeklyVal * ConstantValues2018.PRSI_PERCENT
            val annualPRSI = (basicPrsi - taxCred) * 52
            return Math.round(annualPRSI)
        }else{
            return Math.round(grossPay * ConstantValues2018.PRSI_PERCENT)
        }
    }

    private fun generateTaxCredits(){
        if (marStatus == MaritalStatus.SINGLE){
            taxCreditTotal += ConstantValues2018.TAX_CRED_SINGLE
        }

        if (emplStatus == EmploymentStatus.EMPLOYEE){
            taxCreditTotal += ConstantValues2018.TAX_CRED_EMPLOYED
        }


    }
}

