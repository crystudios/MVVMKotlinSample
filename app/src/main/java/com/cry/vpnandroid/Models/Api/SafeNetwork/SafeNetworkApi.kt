package com.cry.vpnandroid.Models.Api.SafeNetwork

import com.cry.vpnandroid.Models.SafeNetworkData
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import okhttp3.OkHttpClient

interface SafeNetworkApi
{
    //@GET("configuration-15.json")
    //fun loadString(@Query("q") status : String) : Call<String>

    @GET("configuration-15.json")
    fun loadData(@Query("q") status : String) : Call<SafeNetworkData>
}