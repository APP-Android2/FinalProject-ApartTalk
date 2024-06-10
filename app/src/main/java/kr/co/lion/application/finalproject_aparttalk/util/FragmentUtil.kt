package kr.co.lion.application.finalproject_aparttalk.util

import kr.co.lion.application.finalproject_aparttalk.R

enum class MainFragmentName(val id: Int, val str: String) {
    COMMUNITY_FRAGMENT(R.id.community_item, "CommunityFragment"),
    LOCATION_FRAGMENT(R.id.location_item, "LocationFragment"),
    HOME_FRAGMENT(R.id.home_item, "HomeFragment"),
    FACILITY_FRAGMENT(R.id.facility_item, "FacilityFragment"),
    ENTIRE_MENU_FRAGMENT(R.id.entiremenu_item, "EntireMenuFragment"),
}

enum class CommunityTabFragmentName(val str:String) {
    TAB_NOTIFICATION_FRAGMENT("TabNotificationFragment"),
    TAB_QUESTION_FRAGMENT("TabQuestionFragment"),
    TAB_TRADE_FRAGMENT("TabTradeFragment"),
    TAB_ETC_FRAGMENT("TabEtcFragment"),
}

enum class CommunityFragmentName(val str:String) {
    COMMUNITY_SEARCH_FRAGMENT("CommunitySearchFragment"),
    COMMUNITY_DETAIL_FRAGMENT("CommunityDetailFragment"),
    COMMUNITY_ADD_FRAGMENT("CommunityAddFragment"),
    COMMUNITY_MODIFY_FRAGMENT("CommunityModifyFragment"),
}

enum class BroadcastFragmentName(val str: String) {
    BROADCAST_FRAGMENT("BroadcastFragment"),
    BROADCAST_DETAIL_FRAGMENT("BroadcastDetailFragment")
}

enum class AptOperationInfoFragmentName(val str: String) {
    OPERATION_FIRST_FRAGMENT("OperationFirstFragment"),
    OPERATION_SECOND_FRAGMENT("OperationSecondFragment"),
}

// 프래그먼트들의 이름
enum class FireCheckFragmentName(var str:String){
    FIRE_CHECK_MAIN_FRAGMENT("FireCheckMainFragment.kt"),
    FIRE_CHECK_SELF_FRAGMENT("FireCheckSelfFragment.kt"),
}


enum class VoteFragmentName(var str:String){
    VOTE_FRAGMENT("VoteFragment"),
    VOTE_HISTORY_FRAGMENT("VoteHistoryFragment"),
    VOTE_LIST_FRAGMENT("VoteListFragment"),
    VOTE_TAB_FRAGMENT("VoteTabFragment"),
    SURVEY_FRAGMENT("SurveyFragment"),
    SURVEY_LIST_FRAGMENT("SurveyListFragment"),
    SURVEY_WRITE_FRAGMENT("SurveyWriteFragment"),
    SURVEY_COMPLETE_FRAGMENT("SurveyCompleteFragment")
}

enum class InfoFragmentName(var str:String){
    EDIT_USER_INFO_FRAGMENT("EditUserInfoFragment.kt"),
    INFO_FRAGMENT("InfoFragment.kt")
}

enum class ServiceFragmentName(var str:String){
    SERVICE_FRAGMENT("ServiceFragment.kt"),
    SINGLE_SERVICE_FRAGMENT("SingleServiceFragment,kt"),
    VIEW_MY_Q_FRAGMENT("ViewMyQFragment.kt"),
    MY_Q_FRAGMENT("MyQFragment.kt"),
    F_A_Q_FRAGMENT("FAQFragment.kt"),
    ANNOUNCEMENT_FRAGMENT("AnnouncementFragment.kt"),
    VIEW_ANNOUNCEMENT_FRAGMENT("ViewAnnouncementFragment.kt"),
    VIEW_FAQ_FRAGMENT("ViewFAQFragment.kt"),
    VIEW_MYQ_FRAGMENT("ViewMyQFragment.kt")
}

enum class MyWriteFragmentName(var str:String){
    MY_WRITE_FRAGMENT("MyWriteFragment.kt"),
    MY_WROTE_FRAGMENT("MyWroteFragment.kt"),
    MY_LIKE_FRAGMENT("MyLikeFragment.kt")
}

enum class ReserveFragmentName(var str:String){
    RESERVATION_FRAGMENT("ReservationFragment.kt"),
    RESERVATION_CONFIRM_FRAGMENT("ReservationConfirmFragment.kt"),
    RESERVATION_COMPLETE_FRAGMENT("ReservationCompleteFragment.kt"),
    RESERVATION_CANCEL_FRAGMENT("ReservationCancelFragment.kt"),
    RESERVATION_CANCEL_COMPLETE_FRAGMENT("ReservationCancelCompleteFragment.kt")
}

enum class ParkingFragmentName(var str:String){
    PARKING_CHECK_FRAGMENT("ParkingCheckFragment"),
    PARKING_RESERVE_FRAGMENT("ParkingReserveFragment")
}

enum class InquiryFragmentName(var str:String){
    INQUIRY_FRAGMENT("InquiryFragment"),
    INQUIRY_COMPLETE_FRAGMENT("InquiryCompleteFragment"),
    INQUIRY_WRITE_FRAGMENT("InquiryWriteFragment"),
    INQUIRING_FRAGMENT("InquiringFragment"),
    INQUIRY_TAB_FRAGMENT("InquiryTabFragment")

}