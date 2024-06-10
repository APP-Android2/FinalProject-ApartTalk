package kr.co.lion.application.finalproject_aparttalk

import android.app.Application
import kr.co.lion.application.finalproject_aparttalk.auth.FirebaseAuthService
import kr.co.lion.application.finalproject_aparttalk.db.local.LocalApartmentDataSource
import kr.co.lion.application.finalproject_aparttalk.db.local.LocalUserDataSource
import kr.co.lion.application.finalproject_aparttalk.db.remote.ApartmentDataSource
import kr.co.lion.application.finalproject_aparttalk.db.remote.UserDataSource
import kr.co.lion.application.finalproject_aparttalk.repository.ApartmentRepository
import kr.co.lion.application.finalproject_aparttalk.repository.AuthRepository
import kr.co.lion.application.finalproject_aparttalk.repository.UserRepository
import kr.co.lion.application.finalproject_aparttalk.util.PreferenceUtil

class App : Application() {

    companion object{
        lateinit var authRepository: AuthRepository
        lateinit var userRepository: UserRepository
        lateinit var apartmentRepository: ApartmentRepository
        lateinit var prefs: PreferenceUtil
    }

    override fun onCreate() {
        super.onCreate()
        authRepository = AuthRepository(FirebaseAuthService())
        userRepository = UserRepository(UserDataSource(), LocalUserDataSource(applicationContext))
        apartmentRepository = ApartmentRepository(ApartmentDataSource(), LocalApartmentDataSource(applicationContext))
        prefs = PreferenceUtil(applicationContext)
    }
}