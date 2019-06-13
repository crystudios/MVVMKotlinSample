package com.cry.vpnandroid.Models

data class Ads (

	val enabled : Boolean,
	val houseAd : Boolean,
	val network : String,
	val priority : Int,
	val totalPresented : Int,
	val type : String,
	val unitId : String
)