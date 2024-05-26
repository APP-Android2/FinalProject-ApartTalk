package kr.co.lion.application.finalproject_aparttalk.util

enum class MainFragmentName(val str:String){
    COMMUNITY_FRAGMENT("CommunityFragment"),
    LOCATION_FRAGMENT("LocationFragment"),
    HOME_FRAGMENT("HomeFragment"),
    FACILITY_FRAGMENT("FacilityFragment"),
    ENTIRE_MENU_FRAGMENT("EntireMenuFragment"),
    ALARM_FRAGMENT("AlarmFragment")
}

// 프래그먼트들의 이름
enum class FireCheckFragmentName(var str:String){
    FIRE_CHECK_MAIN_FRAGMENT("FireCheckMainFragment.kt"),
    FIRE_CHECK_SELF_FRAGMENT("FireCheckSelfFragment.kt"),
}