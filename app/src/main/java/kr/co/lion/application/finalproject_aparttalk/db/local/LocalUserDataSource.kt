package kr.co.lion.application.finalproject_aparttalk.db.local

import android.content.Context
import com.google.gson.Gson
import kr.co.lion.application.finalproject_aparttalk.model.UserModel

class LocalUserDataSource(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()
    private var cachedUser: UserModel? = null

    fun saveUser(user: UserModel) {
        cachedUser = user
        val userJson = gson.toJson(user)
        sharedPreferences.edit().putString("user", userJson).apply()
    }

    fun getUser(): UserModel? {
        if (cachedUser != null) {
            return cachedUser
        }
        val userJson = sharedPreferences.getString("user", null) ?: return null
        cachedUser = gson.fromJson(userJson, UserModel::class.java)
        return cachedUser
    }

    fun clearUser() {
        cachedUser = null
        sharedPreferences.edit().remove("user").apply()
    }
}