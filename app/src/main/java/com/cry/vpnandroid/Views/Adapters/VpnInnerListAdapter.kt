package com.cry.vpnandroid.Views.Adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cry.vpnandroid.Models.Vpn
import com.cry.vpnandroid.R
import com.cry.vpnandroid.Views.Adapters.VpnInnerListAdapter.VpnInnerListViewHolder

class VpnInnerListAdapter ( var list : List<Vpn>): RecyclerView.Adapter<VpnInnerListViewHolder>()
{
    private lateinit var onItemClickListener : OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VpnInnerListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val root = inflater.inflate(R.layout.inner_vpn_list_item, parent, false)
        return VpnInnerListViewHolder(root, list)
    }

    fun updateList(newList : List<Vpn>)
    {
        list = newList
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: VpnInnerListViewHolder, position: Int) {
        holder.bind(position)
    }

    class VpnInnerListViewHolder (val view : View, var list : List<Vpn>) : RecyclerView.ViewHolder(view), View.OnClickListener
    {
        private lateinit var txtServerName : TextView
        private lateinit var imgItemFlag : ImageView
        private lateinit var pnlBackgroundOverlay : View
        private lateinit var imgSelector : ImageView

        override fun onClick(v: View?) {
            selectItem(adapterPosition)
        }

        fun bind(position : Int){
            txtServerName = view.findViewById(R.id.txtServerName)
            imgItemFlag = view.findViewById(R.id.imgItemFlag)
            pnlBackgroundOverlay = view.findViewById(R.id.pnlBackgroundOverlay)
            imgSelector = view.findViewById(R.id.imgSelector)

            var vpn = list[position]
            txtServerName.text = vpn.name + " " + (position + 1).toString()

            view.setOnClickListener(this)
            // Set the image
            val defaultOptions = RequestOptions()
                .error(R.drawable.country_usa)

            Glide.with(view.context)
                .setDefaultRequestOptions(defaultOptions)
                .load("https://cfg.safenetwork.us/" + vpn.imageUrl)
                .into(imgItemFlag)

            //TODO: Implement onItemClick to highlight the selected item
        }


        fun selectItem(position : Int)
        {
            pnlBackgroundOverlay.setBackgroundResource(R.color.listSelectedBackground)
            imgSelector.setImageResource(R.drawable.ic_icon_selected)
            txtServerName.setTextColor(Color.WHITE)
        }
        fun deselectItem(position : Int)
        {
            pnlBackgroundOverlay.setBackgroundResource(R.color.transparentGradientTransparent)
            imgSelector.setImageResource(R.drawable.ic_icon_goto)
            txtServerName.setTextColor(view.context.resources.getColor(R.color.colorSecondaryText))
        }
    }
    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    fun SetOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }
}