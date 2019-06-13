package com.cry.vpnandroid.Views

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.cry.vpnandroid.R
import com.cry.vpnandroid.ViewModels.VipVpnViewModel
import com.cry.vpnandroid.Views.Adapters.VpnListAdapter
import kotlinx.android.synthetic.main.fragment_vip_vpn_list.*

class VipVpnFragment : Fragment() {

    lateinit var viewModel : VipVpnViewModel
    lateinit var mAdapterObserver : Observer<VpnListAdapter>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_vip_vpn_list, container, false)

        viewModel = ViewModelProviders.of(this.activity!!).get(VipVpnViewModel::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        lstVipVpns.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        mAdapterObserver = Observer{
            lstVipVpns.adapter = it
        }
        viewModel.adapter.observe(this, mAdapterObserver)
    }
}
