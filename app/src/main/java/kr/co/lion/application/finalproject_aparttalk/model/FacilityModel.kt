package kr.co.lion.application.finalproject_aparttalk.model

data class FacilityModel(
    val dataId:String? = "",
    var imageRes:String = "",
    val titleText:String = "",
    val canReserve:Boolean = false,
    val price:String? = "",
    val content:String? = "",

)
