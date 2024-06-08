package kr.co.lion.application.finalproject_aparttalk.ui.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.App
import kr.co.lion.application.finalproject_aparttalk.model.UserModel
import kr.co.lion.application.finalproject_aparttalk.repository.UserRepository

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _isUserUpdated = MutableLiveData<Boolean>()
    val isUserUpdated: LiveData<Boolean> get() = _isUserUpdated

    private val _user = MutableLiveData<UserModel?>()
    val user: LiveData<UserModel?> get() = _user

    fun loadUser() {
        viewModelScope.launch {
            val authUser = App.authRepository.getCurrentUser()
            val user = userRepository.getUser(authUser!!.uid)
            _user.value = user
        }
    }

    fun updateUser(updatedUser: UserModel) {
        viewModelScope.launch {
            userRepository.updateUser(updatedUser)
            _isUserUpdated.value = true // 업데이트 후 사용자 정보를 갱신
        }
    }
}