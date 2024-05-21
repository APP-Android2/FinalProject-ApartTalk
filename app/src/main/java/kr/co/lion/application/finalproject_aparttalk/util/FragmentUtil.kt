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