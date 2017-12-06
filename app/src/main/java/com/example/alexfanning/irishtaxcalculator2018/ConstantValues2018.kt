package com.example.alexfanning.irishtaxcalculator2018

import java.util.*

/**
 * Created by alex.fanning on 16/11/2017.
 */
object ConstantValues2018 {
    const val STANDARD_RATE_TAX_BAND_SINGLE = 34550
    const val STANDARD_RATE_TAX_BAND_MARRIED = 43550

    const val LOWER_RATE_PERCENT = .2f
    const val HIGHER_RATE_PERCENT = .4f

    const val TAX_CRED_SINGLE = 1650
    const val TAX_CRED_EMPLOYED = 1650

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
    SINGLE(0), MARRIED_ONE_WORKING(1), MARRIED_TWO_WORKING(2), LONE_PARENT(3), WIDOWED(4);

    companion object {
        fun from(search: Int): MaritalStatus = requireNotNull(values().find { it.index == search })
    }
}

enum class EmploymentStatus(val index: Int) {
    PAYE_WORKER(0), SELF_EMPLOYED(1), PUBLIC_SERVANT(2);

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