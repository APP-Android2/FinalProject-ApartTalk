package kr.co.lion.application.finalproject_aparttalk.db.local

import android.content.Context
import com.google.gson.Gson
import kr.co.lion.application.finalproject_aparttalk.model.ApartmentModel

class LocalApartmentDataSource(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("apartment_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()
    private var cachedApartment: ApartmentModel? = null

    fun saveApartment(apartment: ApartmentModel) {
        cachedApartment = apartment
        val apartmentJson = gson.toJson(apartment)
        sharedPreferences.edit().putString("apartment", apartmentJson).apply()
    }

    fun getApartment(): ApartmentModel? {
        if (cachedApartment != null) {
            return cachedApartment
        }
        val apartmentJson = sharedPreferences.getString("apartment", null) ?: return null
        cachedApartment = gson.fromJson(apartmentJson, ApartmentModel::class.java)
        return cachedApartment
    }

    fun clearApartment() {
        cachedApartment = null
        sharedPreferences.edit().remove("apartment").apply()
    }
}