package com.cry.vpnandroid.Views.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cry.vpnandroid.Models.Vpn
import com.cry.vpnandroid.R
import com.cry.vpnandroid.Views.Adapters.VpnListAdapter.VpnListViewHolder
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class VpnListAdapter ( var vpnList : List<Vpn>): RecyclerView.Adapter<VpnListViewHolder>()
{
    private var vpnMainList = vpnList.filter { x -> !x.hidden }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VpnListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val root = inflater.inflate(R.layout.list_item_vpn, parent, false)
        return VpnListViewHolder(root, vpnList, vpnMainList)
    }

    fun updateList(newList : List<Vpn>)
    {
        vpnList = newList
        vpnMainList = vpnList.filter { x -> !x.hidden }
    }

    override fun getItemCount(): Int {
        return vpnMainList.count()
    }

    override fun onBindViewHolder(holder: VpnListViewHolder, position: Int) {
        holder.bind(position)
    }

    fun deselectItem(pos : Int, child: Int)
    {
        //TODO: This was not specified on the document, will skip this functionality
    }

    class VpnListViewHolder (val view : View, var fullList : List<Vpn>, var groupsList : List<Vpn>) : RecyclerView.ViewHolder(view)
    {
        private lateinit var headerText : TextView
        private lateinit var imgHeaderFlag : ImageView
        private lateinit var imgSignalStrength : ImageView
        private lateinit var innerListView : RecyclerView
        private lateinit var innerAdapter : VpnInnerListAdapter

        fun bind(position : Int){
            headerText = view.findViewById(R.id.headerText)
            imgHeaderFlag = view.findViewById(R.id.imgHeaderFlag)
            imgSignalStrength = view.findViewById(R.id.imgSignalStrength)
            innerListView = view.findViewById(R.id.innerListView)

            val vpn = groupsList[position]
            val childList = fullList.filter { x -> x.group == vpn.group }.sortedBy { x -> x.hidden }
            val itemCount = groupsList.size

            headerText.text = vpn.name

            innerAdapter = VpnInnerListAdapter(childList)

            innerListView.layoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)
            innerListView.adapter = innerAdapter

            val defaultOptions = RequestOptions()
                .error(R.drawable.country_usa)

            Glide.with(view.context)
                .setDefaultRequestOptions(defaultOptions)
                .load("https://cfg.safenetwork.us/" + vpn.imageUrl)
                .into(imgHeaderFlag)

            if (position == 0 || (itemCount > 2 && position == 1))
            {
                imgSignalStrength.setImageResource(R.drawable.ic_icon_bars_best)
            }
            else if (position == (itemCount-1))
            {
                imgSignalStrength.setImageResource(R.drawable.ic_icon_bars_low)
            }
            else
            {
                imgSignalStrength.setImageResource(R.drawable.ic_icon_bars_medium)
            }
        }
    }
}
