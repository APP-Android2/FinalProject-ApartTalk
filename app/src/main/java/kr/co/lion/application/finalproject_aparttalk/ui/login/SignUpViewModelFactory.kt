package kr.co.lion.application.finalproject_aparttalk.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.co.lion.application.finalproject_aparttalk.repository.UserRepository

class SignUpViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            return SignUpViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
