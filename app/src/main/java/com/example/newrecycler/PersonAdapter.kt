package com.example.newrecycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_item_view.view.*

class CarAdapter(var items : MutableList<Person>, var clickListner: OnCarItemClickListner) : RecyclerView.Adapter<CarViewHolder>(){
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        lateinit var carViewHolder : CarViewHolder
        carViewHolder = CarViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_item_view,parent,false ))
        return carViewHolder
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
//        holder.carName?.text =  items.get(position).name
//        holder.carDescription?.text = items.get(position).description
//        holder.carLogo.setImageResource(items.get(position).logo)
        holder.initialize(items.get(position),clickListner)

    }
}

class CarViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
    val userName: TextView = itemView.tv_name
    val userTitle : TextView = itemView.tv_title
    val userLastname : TextView = itemView.tv_lastname
    val userEmail : TextView = itemView.tv_email
    val userPhone : TextView = itemView.tv_phone

    fun initialize(item: Person, action:OnCarItemClickListner){
        userTitle.setText(item.title)
        userName.setText(item.name)
        userLastname.setText(item.lastname)
        userEmail.setText(item.email)
        userPhone.setText(item.phone)
        itemView.setOnClickListener{
            action.onItemClick(item,adapterPosition)
        }

    }

}

interface OnCarItemClickListner{
    fun onItemClick(item: Person, position: Int)
}