package com.cry.vpnandroid.ViewModels

import android.app.Application
import android.content.Context
import com.cry.vpnandroid.Core.BaseViewModel
import com.cry.vpnandroid.Models.Vpn
import com.cry.vpnandroid.R
import com.cry.vpnandroid.Views.Adapters.VpnListAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FreeVpnViewModel (application : Application) : BaseVpnViewModel(application)
{

    init{
        val json = mApp.getSharedPreferences(mApp.getString(R.string.vpn_json_string_key), Context.MODE_PRIVATE)
            .getString(mApp.getString(R.string.vpn_json_string_key), "")

        val listType = object : TypeToken<ArrayList<Vpn>>(){}.type
        val vpnList = Gson().fromJson(json, listType) as List<Vpn>

        val freeVpnList = vpnList.filter { x -> !x.isPremium }.toMutableList()

        val defVpn = Vpn("flags/015/countryDefault.png",
            true,
            false,
            "Default",
            "",
            "",
            "",
            "",
            "",
            "",
            "def",
            "def",
            false)
        freeVpnList.add(0,defVpn)

        mVpnList.postValue(freeVpnList)

        mRealAdapter = VpnListAdapter(freeVpnList)
        mAdapter.postValue(mRealAdapter)
    }
}

