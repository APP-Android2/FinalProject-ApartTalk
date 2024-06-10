package kr.co.lion.application.finalproject_aparttalk.model

import com.google.firebase.Timestamp

data class FacilityResModel (
    val userUid:String = "",
    val titleText:String = "",
    val useTime:String = "",
    var imageRes:String = "",
    val usePrice:String = "",
    val reservationState:Boolean = true,
    val reservationDate:String = "",
    val userName:String? = "",
    val userNumber:String? = "",
    val reserveTime:Timestamp = Timestamp.now()
)