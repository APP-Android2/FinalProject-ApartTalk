package kr.co.lion.application.finalproject_aparttalk.ui.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.model.UserModel
import kr.co.lion.application.finalproject_aparttalk.repository.AuthRepository
import kr.co.lion.application.finalproject_aparttalk.repository.UserRepository

class LoginViewModel(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _userAuthenticationState = MutableLiveData<NavigationLoginEvent>()
    val userAuthenticationState: LiveData<NavigationLoginEvent> = _userAuthenticationState


    init {
        isAuthenticated()
    }

    private fun isAuthenticated() = viewModelScope.launch {
        val currentUser = authRepository.getCurrentUser() ?: return@launch
        val user = userRepository.getUser(currentUser.uid) ?: return@launch  // =0.8초 소요
        _userAuthenticationState.value = when(user.completeInputUserInfo){
            true -> { NavigationLoginEvent.NavigationToMain }
            false -> { NavigationLoginEvent.NavigationToSignUp }
        }
    }

    fun googleLogin(context: Context) = viewModelScope.launch {
        try {
            val googleCredential = authRepository.getGoogleCredential(context) ?: return@launch
            val authResult = authRepository.signInWithGoogle(googleCredential)
            val authUser = authResult.user ?: return@launch
            val user = userRepository.getUser(authUser.uid)
            if (user == null) {
                val newUser = UserModel(
                    uid = authUser.uid,
                    idx = 0,  // 유저 번호를 설정하는 로직 필요
                    name = authUser.displayName ?: "",  // 사용자 이름 입력 필요
                    loginType = "구글",
                    birthYear = null,
                    birthMonth = null,
                    birthDay = null,
                    gender = "",
                    email = authUser.email ?: "",
                    phoneNumber = authUser.phoneNumber ?: "",
                    agreementCheck1 = false,
                    agreementCheck2 = false,
                    agreementCheck3 = false,
                    apartmentUid = "",
                    apartmentDong = null,
                    apartmentHo = null,
                    completeInputUserInfo = false,
                    apartCertification = false
                )
                userRepository.createUser(newUser)
                // SignUp 이동
                _userAuthenticationState.value = NavigationLoginEvent.NavigationToSignUp
                return@launch
            }

            _userAuthenticationState.value = when(user.completeInputUserInfo){
                true -> { NavigationLoginEvent.NavigationToMain }
                false -> { NavigationLoginEvent.NavigationToSignUp }
            }

        } catch (e: Exception) {
            Log.d("test1234", "${e.message}")
        }
    }
}