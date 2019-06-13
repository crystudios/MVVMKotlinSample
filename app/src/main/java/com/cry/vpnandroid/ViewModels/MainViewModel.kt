package com.cry.vpnandroid.ViewModels

import android.app.Application
import android.content.Context
import android.content.Intent
import android.location.Location
import android.util.Log
import com.cry.vpnandroid.Core.BaseViewModel
import com.cry.vpnandroid.Core.Utils.GeoDistance
import com.cry.vpnandroid.Models.IpData
import com.cry.vpnandroid.Models.Repositories.IpRepository
import com.cry.vpnandroid.Models.Repositories.VpnRepository
import com.cry.vpnandroid.Models.SafeNetworkData
import com.cry.vpnandroid.R
import com.cry.vpnandroid.Views.ServerCountryActivity
import com.fonfon.kgeohash.GeoHash
import com.fonfon.kgeohash.toGeoHash
import com.google.gson.Gson
import java.lang.Exception
import java.util.prefs.Preferences

class MainViewModel(application: Application) : BaseViewModel(application)
{
    val _app = application
    lateinit var currentLocation : Location

    fun init()
    {
        try {
            IpRepository().getIpData {
                handleIpData(it)
                VpnRepository().getVpnList {
                    handleVpnData(it)
                }
            }
        }
        catch (ex: Exception)
        {
            Log.e(TAG, "Failed to get data from API's (probably no network?")
        }
    }

    private fun handleIpData(data : IpData)
    {
        val hash = GeoHash(data.latitude, data.longitude)
        currentLocation = hash.toLocation()
    }
    private fun handleVpnData(data : SafeNetworkData)
    {
        val vpns = data.vpn
        for ( vpn in vpns)
        {
            val loc = vpn.geohash.toGeoHash().toLocation()
            vpn.distance = GeoDistance().distance(currentLocation.latitude, currentLocation.longitude, loc.latitude, loc.longitude, "K")
        }

        val sortedList = vpns.sortedWith(compareBy{ it.distance })
        val vpnJson = Gson().toJson(sortedList)

        val sharedPref = _app.getSharedPreferences(_app.resources.getString(R.string.vpn_json_string_key), Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString(_app.resources.getString(R.string.vpn_json_string_key), vpnJson)
            apply()
        }
        finishLoading()
    }
    private fun finishLoading()
    {
        val intent = Intent(_app, ServerCountryActivity::class.java)
        //intent.putExtra("IpExtra", data.ip)
        _app.startActivity(intent)

    }
}