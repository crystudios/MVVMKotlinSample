package com.cry.vpnandroid.Models

data class Vpn (
	val imageUrl : String,
	val isDefault : Boolean,
	val isPremium : Boolean,
	val name : String,
	val serverUrl : String,
	val configurationUrl : String,
	val trialConfigurationUrl : String,
	val vpnProtocol : String,
	val configurationFallbackUrl : String,
	val geohash : String,
	val group : String,
	val continent : String,
	val hidden : Boolean,
	var distance : Double = 0.0
)