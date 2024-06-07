package kr.co.lion.application.finalproject_aparttalk.model

data class FacilityResModel (
    val userUid:String = "",
    val titleText:String = "",
    val useTime:String = "",
    var imageRes:String = "",
    val usePrice:String = "",
    val reservationState:Boolean = true,
    val reservationDate:String = ""
)