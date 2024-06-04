package kr.co.lion.application.finalproject_aparttalk.ui.login.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.App
import kr.co.lion.application.finalproject_aparttalk.model.UserModel
import kr.co.lion.application.finalproject_aparttalk.repository.AuthRepository
import kr.co.lion.application.finalproject_aparttalk.repository.UserRepository
import kr.co.lion.application.finalproject_aparttalk.ui.login.NavigationState

class LoginViewModel(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _userAuthenticationState = MutableLiveData<NavigationState>().apply { value =
        NavigationState.TO_LOGIN
    }
    val userAuthenticationState: LiveData<NavigationState> = _userAuthenticationState

    init {
        isAuthenticated()
    }

    private fun isAuthenticated() = viewModelScope.launch(Dispatchers.Main) {
        val currentUser = authRepository.getCurrentUser()
        if (currentUser == null) {
            _userAuthenticationState.value = NavigationState.TO_LOGIN
            return@launch
        }
        val user = userRepository.getUser(currentUser.uid)  // =0.8초 소요
        _userAuthenticationState.value = when (user?.completeInputUserInfo) {
            true -> {
                NavigationState.TO_MAIN
            }
            false -> {
                NavigationState.TO_SIGNUP
            }
            else -> {
                NavigationState.TO_LOGIN
            }
        }

    }

    fun googleLogin(context: Context) = viewModelScope.launch(Dispatchers.Main) {
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
                _userAuthenticationState.value = NavigationState.TO_SIGNUP
                return@launch
            }

            _userAuthenticationState.value = when (user.completeInputUserInfo) {
                true -> {
                    val apartment = App.apartmentRepository.getApartment(user.apartmentUid)
                    if (apartment != null){
                        App.prefs.setLatitude(apartment.latitude)
                        App.prefs.setLongitude(apartment.longitude)
                    }
                    NavigationState.TO_MAIN
                }
                false -> {
                    NavigationState.TO_SIGNUP
                }
            }

        } catch (e: Exception) {
            Log.d("test1234", "${e.message}")
        }
    }
}