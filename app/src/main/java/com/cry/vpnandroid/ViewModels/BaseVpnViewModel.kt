package com.cry.vpnandroid.ViewModels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cry.vpnandroid.Core.BaseViewModel
import com.cry.vpnandroid.Models.Vpn
import com.cry.vpnandroid.Views.Adapters.VpnListAdapter

open class BaseVpnViewModel (application : Application) : BaseViewModel(application)
{
    protected var mApp = application
    protected var mVpnList = MutableLiveData<MutableList<Vpn>>()
    val vpnList : LiveData<MutableList<Vpn>> get() = mVpnList

    protected lateinit var mRealAdapter : VpnListAdapter
    protected var mAdapter = MutableLiveData<VpnListAdapter>()
    val adapter : LiveData<VpnListAdapter> get() = mAdapter

    //TODO: Item Selection / Deselection is not on the scope of the task
    //selectedItem (parent, child)
    //fun selectNewItem(parentIndex : Int, childIndex : Int) { ... }

    init{

    }

    fun setAdapter(list : MutableList<Vpn>)
    {
        mRealAdapter.updateList(list)
        mRealAdapter.notifyDataSetChanged()
        mAdapter.postValue(mRealAdapter)
    }

    //setAdapter

}