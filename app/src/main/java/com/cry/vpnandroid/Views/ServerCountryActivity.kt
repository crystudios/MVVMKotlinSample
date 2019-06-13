package com.cry.vpnandroid.Views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.cry.vpnandroid.R
import com.cry.vpnandroid.Views.Adapters.TabAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_server_country.*
import kotlinx.android.synthetic.main.activity_server_country.view.*

class ServerCountryActivity : AppCompatActivity(){

    private lateinit var ipAddress : String
    private lateinit var adapter : TabAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_server_country)

        //ipAddress = intent.extras["IpExtra"].toString()
        //Toast.makeText(this, ipAddress, Toast.LENGTH_LONG).show()

        adapter = TabAdapter(supportFragmentManager)

        adapter.addFragment(FreeVpnFragment(), "Free")
        adapter.addFragment(VipVpnFragment(), "VIP")

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

    }
}
