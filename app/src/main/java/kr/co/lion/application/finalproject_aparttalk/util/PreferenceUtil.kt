package kr.co.lion.application.finalproject_aparttalk.util

import android.content.Context
import android.content.SharedPreferences

class PreferenceUtil(context: Context) {

    private val prefsGeoPoint: SharedPreferences = context.getSharedPreferences("prefsGeoPoint", Context.MODE_PRIVATE)

    fun setLatitude(latitude: String) {
        prefsGeoPoint.edit().putString("latitude", latitude).apply()
    }

    fun setLongitude(longitude: String) {
        prefsGeoPoint.edit().putString("longitude", longitude).apply()
    }

    fun getLatitude(defValue: String): String {
        return prefsGeoPoint.getString("latitude", defValue).toString()
    }

    fun getLongitude(defValue: String): String {
        return prefsGeoPoint.getString("longitude", defValue).toString()
    }
}