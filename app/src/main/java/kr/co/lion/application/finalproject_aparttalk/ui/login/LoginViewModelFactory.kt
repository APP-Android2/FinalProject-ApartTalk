package kr.co.lion.application.finalproject_aparttalk.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.co.lion.application.finalproject_aparttalk.repository.ApartmentRepository
import kr.co.lion.application.finalproject_aparttalk.repository.AuthRepository
import kr.co.lion.application.finalproject_aparttalk.repository.UserRepository
import kr.co.lion.application.finalproject_aparttalk.ui.login.viewmodel.LoginViewModel

class LoginViewModelFactory(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
    private val apartmentRepository: ApartmentRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(authRepository, userRepository, apartmentRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
