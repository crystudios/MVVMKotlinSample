package com.cry.vpnandroid.Models.Repositories

import com.cry.vpnandroid.Models.Api.IpConfig.IpConfigClient
import com.cry.vpnandroid.Models.IpData

class IpRepository
{
    fun getIpData(onSuccess : (IpData) -> Unit)
    {
        IpConfigClient().loadIpData{
            onSuccess(it)
        }
    }
}