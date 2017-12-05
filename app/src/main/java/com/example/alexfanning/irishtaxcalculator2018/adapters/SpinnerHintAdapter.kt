package com.example.alexfanning.irishtaxcalculator2018.adapters

import android.content.Context
import android.widget.ArrayAdapter
import java.util.*

/**
 * Created by alex.fanning on 05/12/2017.
 */


public class SpinnerHintAdapter(theContext: Context, resource: Int, objects: Array<String>) : ArrayAdapter<Any>(theContext, resource, objects) {

    override fun getCount(): Int {
        // don't display last item. It is used as hint.
        val count = super.getCount()
        return if (count > 0) count - 1 else count
    }
}