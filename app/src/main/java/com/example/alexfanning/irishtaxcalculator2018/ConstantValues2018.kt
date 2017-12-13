package com.example.alexfanning.irishtaxcalculator2018

import java.text.DecimalFormat
import java.util.*

/**
 * Created by alex.fanning on 16/11/2017.
 */
object ConstantValues2018 {
    const val STANDARD_RATE_TAX_BAND_SINGLE_NO_CHILDR = 34550
    const val STANDARD_RATE_TAX_BAND_SINGLE_WITH_CHILD = 37800
    const val STANDARD_RATE_TAX_BAND_MARRIED_ONE_WORKING = 43550
    const val STANDARD_RATE_TAX_BAND_MARRIED_BOTH_WORKING = 43550

    const val STANDARD_RATE_CUT_OFF_SPOUSE_INCREASE = 24800

    const val LOWER_RATE_PERCENT = .2f
    const val HIGHER_RATE_PERCENT = .4f

    const val TAX_CRED_SINGLE = 1650
    const val TAX_CRED_EMPLOYED = 1650
    const val TAX_CRED_SELF_EMPLOYEED = 1150
    const val TAX_CRED_WIDOWED_NO_CHILDREN = 2190
    const val TAX_CRED_WIDOWED_WITH_CHILDREN = 1650
    const val TAX_CRED_MARRIED = 3300

    val USC_VALS: Map<Long, Float> = mapOf<Long, Float>(0L to .005f, 12012L to .02f, 19372L to .0475f, 70044L to .08f)


    const val PRSI_PERCENT = .04f
    const val PRSI_LOWER_CUT_OFF_WEEKLY = 352.01f
    const val PRSI_HIGHER_CUT_OFF_WEEKLY = 424
    const val PRSI_LOWER_CUT_OFF_ANNUAL = 18304
    const val PRSI_HIGHER_CUT_OFF_ANNUAL = 22048
    const val PRSI_TAX_CRED = 12
    const val PRSI_TAX_CRED_DIVISBLE_FACTOR = 6

    const val WEEKS_IN_YEAR = 52
}

enum class MaritalStatus(val index: Int) {
    SINGLE(0),SINGLE_LONE_PARENT (1), WIDOWED(2), WIDOWED_LONE_PARENT(3),MARRIED_ONE_WORKING(4),MARRIED_TWO_WORKING(5);

    companion object {
        fun from(search: Int): MaritalStatus = requireNotNull(values().find { it.index == search })
    }
}

enum class EmploymentStatus(val index: Int) {
    PAYE_WORKER(0), SELF_EMPLOYED(1), PUBLIC_SERVANT(2), NO_SPOUSE(3);

    companion object {
        fun from(search: Int): EmploymentStatus = requireNotNull(values().find { it.index == search })
    }
}

enum class AgeStatus(val index: Int) {
    UNDER_65(0), _65(1), _65_69(2), OVER_69(3);

    companion object {
        fun from(search: Int): AgeStatus = requireNotNull(values().find { it.index == search })
    }
}

enum class ChildStatus(val index: Int) {
    NONE(0), _1(1), _2(2), _3(3),_4(4), _5(5), _6(6),_7(7), _8(8);

    companion object {
        fun from(search: Int): ChildStatus = requireNotNull(values().find { it.index == search })
    }
}

//String Extension Method
fun String.formatResult() : String{
    val amount = this
    val formatter = DecimalFormat("#,###.00")
    val numFormatted = formatter.format(amount.toDouble())
    val sb = StringBuilder()
    sb.append("€")
    sb.append(numFormatted)
    return sb.toString()
}