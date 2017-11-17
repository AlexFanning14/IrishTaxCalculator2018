package com.example.alexfanning.irishtaxcalculator2018.navdrawer

import android.app.Activity
import android.content.Context
import android.support.annotation.NonNull
import android.support.annotation.Nullable
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.alexfanning.irishtaxcalculator2018.R

/**
 * Created by alex.fanning on 17/11/2017.
 */
class DrawerItemCustomAdapter(var mContext:Context, var layoutResourceID:Int, var items: Array<NavDrawerItem?>): ArrayAdapter<NavDrawerItem>(mContext, layoutResourceID, items) {

    override fun getView(position:Int, @Nullable convertView: View, @NonNull parent: ViewGroup):View {
        var listItem = convertView
        val inflater = (mContext as Activity).getLayoutInflater()
        listItem = inflater.inflate(layoutResourceID, parent, false)
        val textViewName = listItem.findViewById<TextView>(R.id.tv_drawer)
        val item = items[position]
        textViewName.setText(item?.name)
        return listItem
    }
    override fun getItemId(position:Int):Long {
        return super.getItemId(position)
    }
}

class NavDrawerItem(var name:String)