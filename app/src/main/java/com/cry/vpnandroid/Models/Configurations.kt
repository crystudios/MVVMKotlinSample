package com.cry.vpnandroid.Models

data class Configurations (

	val applicationConfiguration : ApplicationConfiguration,
	val subscriptionText : String,
	val screens : List<Screens>,
	val settingsMenuItems : List<SettingsMenuItems>,
	val faqItems : List<FaqItems>,
	val validationSharedSecret : String,
	val appseeKey : String,
	val admobKey : String,
	val appsflyerKey : String,
	val zendeskAppId : String,
	val zendeskClientId : String,
	val mopubHouseAdKey : String,
	val mopubInterstitalKey : String,
	val mopubBigBannerKey : String,
	val mopubRewardedKey : String,
	val admobOverride : Boolean,
	val validationUrl : String,
	val mixpanelKey : String,
	val cancelOptions : List<String>,
	val bandwidthConfiguration : BandwidthConfiguration,
	val mpo : Boolean,
	val rbv : Int,
	val build : Int,
	val avarageConnections : Int,
	val badConnections : Int
)