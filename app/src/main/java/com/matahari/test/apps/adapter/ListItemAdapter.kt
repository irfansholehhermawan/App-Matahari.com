package com.matahari.test.apps.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.matahari.test.apps.R
import com.matahari.test.apps.model.ResponseModel
import kotlinx.android.synthetic.main.item_list.view.*

/**
 * Created by Irfan Sholeh Hermawan on 30/07/19.
 * @author
 */

class ListItemAdapter(private val dataList: List<ResponseModel>, private val context: Context) :
    RecyclerView.Adapter<ListItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.name.text = dataList[position].name
        holder.email.text = dataList[position].email
        holder.address.text = dataList[position].address.street + ", " + dataList[position].address.suite + ", " + dataList[position].address.city
        holder.phone.text = dataList[position].phone

        val ICON_URL = "https://cdn3.iconfinder.com/data/icons/user-with-laptop/100/user-laptop-512.png"
        Glide.with(holder.itemView.context).load(ICON_URL).into(holder.itemView.picture)

        for(i in 1..dataList.size step 2){
            if (dataList[position].id == i) {
                holder.itemView.group.visibility = View.GONE
            } else if (dataList[position].id == 1) {
                holder.itemView.group.visibility = View.VISIBLE
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val name = itemView.name!!
        var email = itemView.email!!
        var address = itemView.address!!
        var phone = itemView.phone!!
    }
}