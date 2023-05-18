package com.example.governmentappointmentapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.king.mysimplefirebasedatabaseapp.Appointment

class CustomAdapter(var context: Context, var data:ArrayList<Appointment>):BaseAdapter() {
    private class ViewHolder(row:View?){
        var mTxtName:TextView
        var mTxtEmail:TextView
        var mTxtReason:TextView
        init {
            this.mTxtName = row?.findViewById(R.id.mTvName) as TextView
            this.mTxtEmail = row?.findViewById(R.id.mTvEmail) as TextView
            this.mTxtReason = row?.findViewById(R.id.mTvReason) as TextView
        }
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view:View?
        var viewHolder:ViewHolder
        if (convertView == null){
            var layout = LayoutInflater.from(context)
            view = layout.inflate(R.layout.item_layout,parent,false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        var item:Appointment = getItem(position) as Appointment
        viewHolder.mTxtName.text = item.name
        viewHolder.mTxtEmail.text = item.email
        viewHolder.mTxtReason.text = item.reason
        return view as View
    }

    override fun getItem(position: Int): Any {
        return  data.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data.count()
    }
}