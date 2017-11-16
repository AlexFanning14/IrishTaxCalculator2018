package com.example.alexfanning.irishtaxcalculator2018

import java.util.*
import kotlin.collections.HashMap

/**
 * Created by alex.fanning on 16/11/2017.
 */
data class Calculation(val grossPay: Int,val status: MaritialStatus) {

    var taxToPay : Float = 0f
    var PRSI : Int = 0
    var USC: Int = 0
    var netIncome: Int = 0


    fun calculateTotalTax(){
        var totalTax : Float =0f

        if (grossPay > ConstantValues2018.STANDARD_RATE_TAX_BAND_SINGLE){
            val remainder = grossPay - ConstantValues2018.STANDARD_RATE_TAX_BAND_SINGLE
            val lowerTax = ConstantValues2018.STANDARD_RATE_TAX_BAND_SINGLE * ConstantValues2018.LOWER_RATE_PERCENT
            val higherTax = remainder * ConstantValues2018.HIGHER_RATE_PERCENT
            totalTax = lowerTax + higherTax
        }else{
            totalTax = grossPay * ConstantValues2018.LOWER_RATE_PERCENT
        }
        taxToPay = totalTax
        USC = calculateUSC()


    }

    fun calculateUSC(): Int{
        if (grossPay < 13000){
            return 0
        }
        val keys : List<Long> = ConstantValues2018.USC_VALS.keys.toList()
        var totalUsc : Float
        var i : Int = 0
        while (i < keys.size){
            if (grossPay > keys[i] && grossPay > keys[i + 1]){
                val valToTax = keys[i + 1] - keys[i]
                val uscPC : Float? = ConstantValues2018.USC_VALS.get(keys[i + 1])
                //TODO 
            }
        }
        return 0
    }



}

 enum class MaritialStatus{
    SINGLE,MARRIED, WIDOWED
}