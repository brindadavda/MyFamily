package com.application.myfamily

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.zip.Inflater

class MemberAdapter(private val listMembers: List<MemberModel>):
    RecyclerView.Adapter<MemberAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val item = inflater.inflate(R.layout.item_member,parent,false)

        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = listMembers[position]

        holder.tvName.text = item.name
        holder.tvAddress.text = item.address
        holder.tvBattery.text = item.battery
        holder.tvDistance.text = item.distance

    }

    override fun getItemCount(): Int {
        return listMembers.size
    }


    class ViewHolder(private val item : View) : RecyclerView.ViewHolder(item) {
        val ivPerson = item.findViewById<ImageView>(R.id.ivPerson)
        val tvName = item.findViewById<TextView>(R.id.tvName)
        val tvAddress = item.findViewById<TextView>(R.id.tvLocation)
        val tvBattery = item.findViewById<TextView>(R.id.tvBattery)
        val tvDistance = item.findViewById<TextView>(R.id.tvDistance)
    }
}