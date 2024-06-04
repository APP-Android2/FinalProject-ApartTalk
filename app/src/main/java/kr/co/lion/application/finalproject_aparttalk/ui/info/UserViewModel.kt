package kr.co.lion.application.finalproject_aparttalk.ui.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.model.UserModel
import kr.co.lion.application.finalproject_aparttalk.repository.UserRepository

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _user = MutableLiveData<UserModel?>()
    val user: LiveData<UserModel?> get() = _user

    fun loadUser(uid: String) {
        viewModelScope.launch {
            val user = userRepository.getUser(uid)
            _user.value = user
        }
    }

    fun updateUser(updatedUser: UserModel) {
        viewModelScope.launch {
            userRepository.updateUser(updatedUser)
            _user.value = updatedUser // 업데이트 후 사용자 정보를 갱신
        }
    }
}