package com.cry.vpnandroid.Models.Repositories

import com.cry.vpnandroid.Models.Api.SafeNetwork.SafeNetworkClient
import com.cry.vpnandroid.Models.SafeNetworkData

class VpnRepository
{
    fun getVpnJson(onSuccess : (String) -> Unit)
    {
        SafeNetworkClient().loadString{
            onSuccess(it)
        }
    }
    fun getVpnList(onSuccess: (SafeNetworkData) -> Unit)
    {
        SafeNetworkClient().loadData{
            onSuccess(it)
        }
    }
}