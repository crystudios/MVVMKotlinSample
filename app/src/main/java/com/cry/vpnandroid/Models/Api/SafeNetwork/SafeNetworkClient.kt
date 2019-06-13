package com.cry.vpnandroid.Models.Api.SafeNetwork

import android.util.Log
import com.cry.vpnandroid.Models.IpData
import com.cry.vpnandroid.Models.SafeNetworkData
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class SafeNetworkClient
{
    val BASE_URL = "https://cfg.safenetwork.us/android-test/"

    fun loadString(onSuccess : (String)-> Unit)
    {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url(BASE_URL + "configuration-15.json")
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback{
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                Log.e("ohttp3Error", e.message)
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                onSuccess(response.body()!!.string())
            }
        })

        /*val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val vpnApi = retrofit.create(SafeNetworkApi::class.java)

        val call = vpnApi.loadString("status:open")

        call.enqueue(object : Callback<String>
        {
            override fun onFailure(call: Call<String>?, t: Throwable?) {
                Log.v("retrofit", t!!.message)
            }

            override fun onResponse(call: Call<String>?, response: Response<String>?) {
                onSuccess(response!!.body()!!)
            }
        })*/
    }

    fun loadData(onSuccess: (SafeNetworkData) -> Unit)
    {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val vpnApi = retrofit.create(SafeNetworkApi::class.java)

        val call = vpnApi.loadData("status:open")

        call.enqueue(object : Callback<SafeNetworkData>
        {
            override fun onFailure(call: Call<SafeNetworkData>?, t: Throwable?) {
                Log.e("retrofit", t!!.message)
            }

            override fun onResponse(call: Call<SafeNetworkData>?, response: Response<SafeNetworkData>?) {
                onSuccess(response!!.body()!!)
            }
        })
    }
}