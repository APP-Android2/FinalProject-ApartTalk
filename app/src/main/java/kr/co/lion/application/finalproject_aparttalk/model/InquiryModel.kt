package kr.co.lion.application.finalproject_aparttalk.model

import android.os.Parcel
import android.os.Parcelable

data class InquiryModel(
    var inquiryIdx: Int = 0,
    var inquiryTitle: String = "",
    var inquiryContent: String = "",
    var inquiryAnswer: String = "",
    var inquiryState: Boolean = false,
    var inquiryPrivate: Boolean = false,
    var apartmentUid: String = "",
    var userIdx: String = "",
    var userName: String = "",
    var timestamp: Long = System.currentTimeMillis() // 현재 시간을 기본값으로 설정
)