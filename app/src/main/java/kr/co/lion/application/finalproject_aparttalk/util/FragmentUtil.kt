package kr.co.lion.application.finalproject_aparttalk.util

enum class MainFragmentName(val str:String){
    COMMUNITY_FRAGMENT("CommunityFragment"),
    LOCATION_FRAGMENT("LocationFragment"),
    HOME_FRAGMENT("HomeFragment"),
    FACILITY_FRAGMENT("FacilityFragment"),
    ENTIRE_MENU_FRAGMENT("EntireMenuFragment"),
    ALARM_FRAGMENT("AlarmFragment"),
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
    BROADCAST_DETIAL_FRAGMENT("BroadcastDetailFragment")
}

// 프래그먼트들의 이름
enum class FireCheckFragmentName(var str:String){
    FIRE_CHECK_MAIN_FRAGMENT("FireCheckMainFragment.kt"),
    FIRE_CHECK_SELF_FRAGMENT("FireCheckSelfFragment.kt"),
}