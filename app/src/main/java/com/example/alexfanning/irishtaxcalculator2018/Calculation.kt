package com.example.alexfanning.irishtaxcalculator2018

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import android.support.annotation.IntegerRes
import android.util.Log
import kotlinx.android.parcel.Parcelize
import java.text.DecimalFormat
import java.util.*
import kotlin.collections.HashMap

/**
 * Created by alex.fanning on 16/11/2017.
 */
data class Calculation(val grossPay: Int, val emplStatus: EmploymentStatus, val marStatus: MaritalStatus,
                       val ageStatus: AgeStatus, val hasMedCard: Boolean) : Parcelable {

    constructor(grossPay: Int, emplStatus: EmploymentStatus, marStatus: MaritalStatus,
                ageStatus: AgeStatus, hasMedCard: Boolean, _spouseEmploymentStatus: EmploymentStatus,
                _spouseSal: Int) : this(grossPay, emplStatus, marStatus, ageStatus, hasMedCard) {
        spouseEmploymentStatus = _spouseEmploymentStatus
        spouseSal = _spouseSal
    }

    var incomeTax: Int = 0
    var PRSI: Int = 0
    var USC: Int = 0
    var netIncome: Int = 0
    var taxCreditTotal: Int = 0
    var lowerTax: Float = 0f
    var higherTax: Float = 0f
    var totalTax: Int = 0
    var annualNet: Int = 0
    var monthlyNet: Float = 0f
    var weeklyNet: Float = 0f
    var spouseEmploymentStatus = EmploymentStatus.NO_SPOUSE
    var spouseSal: Int = 0
    var totalGrossPay: Int = 0


    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            EmploymentStatus.from(parcel.readInt()),
            MaritalStatus.from(parcel.readInt()),
            AgeStatus.from(parcel.readInt()),
            parcel.readByte() != 0.toByte()) {
        incomeTax = parcel.readInt()
        PRSI = parcel.readInt()
        USC = parcel.readInt()
        netIncome = parcel.readInt()
        taxCreditTotal = parcel.readInt()
        lowerTax = parcel.readFloat()
        higherTax = parcel.readFloat()
        totalTax = parcel.readInt()
        annualNet = parcel.readInt()
        monthlyNet = parcel.readFloat()
        weeklyNet = parcel.readFloat()
        totalGrossPay = parcel.readInt()
    }


    fun calculateTotalTax() {
        generateTaxCredits()

        totalGrossPay = grossPay

        if (marStatus == MaritalStatus.MARRIED_TWO_WORKING){
            calculateIncomeTaxBothWorking()
        }else{
            calculateIncomeTaxNormal()
        }

        calculatePrsiAndUsc()



        incomeTax -= taxCreditTotal

        if (incomeTax < 0) {
            incomeTax = 0
        }

        totalTax = incomeTax + USC + PRSI
        netIncome = totalGrossPay - totalTax

        annualNet = netIncome
        monthlyNet = netIncome / 12f
        weeklyNet = netIncome / 52f

    }

    private fun calculateIncomeTaxNormal(){
        var incomeTaxVar: Float = 0f
        var tax_band : Int = 0

        if(marStatus == MaritalStatus.SINGLE)
            tax_band = ConstantValues2018.STANDARD_RATE_TAX_BAND_SINGLE_NO_CHILDR
        else if (marStatus == MaritalStatus.SINGLE_LONE_PARENT || marStatus == MaritalStatus.WIDOWED_LONE_PARENT)
            tax_band = ConstantValues2018.STANDARD_RATE_TAX_BAND_SINGLE_WITH_CHILD
        else if (marStatus == MaritalStatus.MARRIED_ONE_WORKING)
            tax_band = ConstantValues2018.STANDARD_RATE_TAX_BAND_MARRIED_ONE_WORKING


        if (totalGrossPay > tax_band) {
            val remainder = totalGrossPay - tax_band
            lowerTax = tax_band * ConstantValues2018.LOWER_RATE_PERCENT
            higherTax = remainder * ConstantValues2018.HIGHER_RATE_PERCENT
            incomeTaxVar = lowerTax + higherTax
        } else {
            incomeTaxVar = totalGrossPay * ConstantValues2018.LOWER_RATE_PERCENT
        }
        incomeTax = Math.round(incomeTaxVar)
    }

    private fun calculateIncomeTaxBothWorking(){
        totalGrossPay += spouseSal

        var lowerSal : Int = 0
        if (grossPay >= spouseSal )
            lowerSal = spouseSal
        else
            lowerSal = grossPay

        var lowerTaxBand : Int = 0
        if (lowerSal <= ConstantValues2018.STANDARD_RATE_CUT_OFF_SPOUSE_INCREASE)
            lowerTaxBand = lowerSal
        else
            lowerTaxBand = ConstantValues2018.STANDARD_RATE_CUT_OFF_SPOUSE_INCREASE

        val totalHigherBand = ConstantValues2018.STANDARD_RATE_TAX_BAND_MARRIED_BOTH_WORKING + lowerTaxBand
        var incomeTaxVar : Float = 0f

        if (totalGrossPay > totalHigherBand) {
            val remainder = totalGrossPay - totalHigherBand
            lowerTax = totalHigherBand * ConstantValues2018.LOWER_RATE_PERCENT
            higherTax = remainder * ConstantValues2018.HIGHER_RATE_PERCENT
            incomeTaxVar = lowerTax + higherTax
        } else {
            incomeTaxVar = totalGrossPay * ConstantValues2018.LOWER_RATE_PERCENT
        }
        incomeTax = Math.round(incomeTaxVar)
    }


    private fun calculatePrsiAndUsc(){
        if (marStatus == MaritalStatus.MARRIED_TWO_WORKING){
            PRSI = calculatePRSI(grossPay)
            PRSI += calculatePRSI(spouseSal)
            USC = calculateUSC(grossPay)
            USC += calculateUSC(spouseSal)
        }else{
            PRSI = calculatePRSI(grossPay)
            USC = calculateUSC(grossPay)
        }
    }



    private fun calculateUSC(sal : Int): Int {
        if (sal < 13000) {
            return 0
        }
        val keys: List<Long> = ConstantValues2018.USC_VALS.keys.toList()
        var totalUsc = 0f
        var i = 0
        while (i < keys.size) {
            try {
                if (sal > keys[i] && sal > keys[i + 1]) {
                    val valToTax = keys[i + 1] - keys[i]
                    val uscPC: Float? = ConstantValues2018.USC_VALS.get(keys[i])
                    totalUsc += (valToTax * uscPC as Float)
                } else if (sal > keys[i]) {
                    val valToTax = sal - keys[i]
                    val uscPC: Float? = ConstantValues2018.USC_VALS.get(keys[i])
                    totalUsc += (valToTax * uscPC as Float)
                }
            } catch (ex: IndexOutOfBoundsException) {
                val valToTax = sal - keys[i]
                val uscPC: Float? = ConstantValues2018.USC_VALS.get(keys[i])
                totalUsc += (valToTax * uscPC as Float)
            }

            i++
        }
        return Math.round(totalUsc)
    }


    private fun calculatePRSI(sal: Int): Int {

        if (sal <= ConstantValues2018.PRSI_LOWER_CUT_OFF_ANNUAL) {
            return 0
        } else if (sal > ConstantValues2018.PRSI_LOWER_CUT_OFF_ANNUAL && sal < ConstantValues2018.PRSI_HIGHER_CUT_OFF_ANNUAL) {
            val weeklyVal = sal.toFloat() / ConstantValues2018.WEEKS_IN_YEAR
            val weeklyDiff = (weeklyVal - ConstantValues2018.PRSI_LOWER_CUT_OFF_WEEKLY) / ConstantValues2018.PRSI_TAX_CRED_DIVISBLE_FACTOR
            val taxCred = ConstantValues2018.PRSI_TAX_CRED - weeklyDiff
            val basicPrsi = weeklyVal * ConstantValues2018.PRSI_PERCENT
            val annualPRSI = (basicPrsi - taxCred) * 52
            return Math.round(annualPRSI)
        } else {
            return Math.round(sal * ConstantValues2018.PRSI_PERCENT)
        }
    }

    private fun generateTaxCredits() {
        if (marStatus == MaritalStatus.SINGLE)
            taxCreditTotal += ConstantValues2018.TAX_CRED_SINGLE
        else if (marStatus == MaritalStatus.WIDOWED )
            taxCreditTotal += ConstantValues2018.TAX_CRED_WIDOWED_NO_CHILDREN
        else if (marStatus == MaritalStatus.MARRIED_ONE_WORKING)
            taxCreditTotal += ConstantValues2018.TAX_CRED_MARRIED
        else if (marStatus == MaritalStatus.MARRIED_TWO_WORKING && spouseEmploymentStatus == EmploymentStatus.PAYE_WORKER) {
            taxCreditTotal += ConstantValues2018.TAX_CRED_MARRIED
            //Spouse PAYE Tax Cred
            taxCreditTotal += ConstantValues2018.TAX_CRED_EMPLOYED
        }else if (marStatus == MaritalStatus.MARRIED_TWO_WORKING && spouseEmploymentStatus == EmploymentStatus.SELF_EMPLOYED) {
            taxCreditTotal += ConstantValues2018.TAX_CRED_MARRIED
            //Spouse PAYE Tax Cred
            taxCreditTotal += ConstantValues2018.TAX_CRED_SELF_EMPLOYEED
        }



        if (emplStatus == EmploymentStatus.PAYE_WORKER) {
            taxCreditTotal += ConstantValues2018.TAX_CRED_EMPLOYED
        }else if (emplStatus == EmploymentStatus.SELF_EMPLOYED){
            taxCreditTotal += ConstantValues2018.TAX_CRED_SELF_EMPLOYEED
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(grossPay)
        parcel.writeInt(emplStatus.index)
        parcel.writeInt(marStatus.index)
        parcel.writeInt(ageStatus.index)
        parcel.writeByte(if (hasMedCard) 1 else 0)
        parcel.writeInt(incomeTax)
        parcel.writeInt(PRSI)
        parcel.writeInt(USC)
        parcel.writeInt(netIncome)
        parcel.writeInt(taxCreditTotal)
        parcel.writeFloat(lowerTax)
        parcel.writeFloat(higherTax)
        parcel.writeInt(totalTax)
        parcel.writeInt(annualNet)
        parcel.writeFloat(monthlyNet)
        parcel.writeFloat(weeklyNet)
        parcel.writeInt(totalGrossPay)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Calculation> {
        override fun createFromParcel(parcel: Parcel): Calculation {
            return Calculation(parcel)
        }

        override fun newArray(size: Int): Array<Calculation?> {
            return arrayOfNulls(size)
        }
    }



}

