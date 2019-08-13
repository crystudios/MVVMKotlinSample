package com.cry.vpnandroid.Views

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.cry.vpnandroid.R
import com.cry.vpnandroid.ViewModels.FreeVpnViewModel
import com.cry.vpnandroid.Views.Adapters.VpnListAdapter
import kotlinx.android.synthetic.main.fragment_free_vpn_list.*


class FreeVpnFragment : Fragment() {

    lateinit var viewModel : FreeVpnViewModel
    lateinit var mAdapterObserver : Observer<VpnListAdapter>

    val htmlText = "The servers under the <b>Free</b> category are free and secured, " +
            "still their connection speed is slower. " +
            "For full speed servers we highly recommend you to check our " +
            "<a href=\"#\" style=\"color:#828fa9\">VIP Servers</a> instead."

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_free_vpn_list, container, false)

        viewModel = ViewModelProviders.of(this.activity!!).get(FreeVpnViewModel::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        lstFreeVpns.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        mAdapterObserver = Observer{
            lstFreeVpns.adapter = it
        }
        viewModel.adapter.observe(this, mAdapterObserver)

        infoText.text = Html.fromHtml(htmlText)
    }
}
