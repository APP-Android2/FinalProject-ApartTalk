package kr.co.lion.application.finalproject_aparttalk.ui.login.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
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

    private val _userAuthenticationState = MutableLiveData<NavigationState>().apply {
        value = NavigationState.TO_LOGIN
    }
    val userAuthenticationState: LiveData<NavigationState> = _userAuthenticationState

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        isAuthenticated()
    }

    private fun isAuthenticated() = viewModelScope.launch {
        val currentUser = authRepository.getCurrentUser()
        if (currentUser == null) {
            _userAuthenticationState.value = NavigationState.TO_LOGIN
            return@launch
        }
        val user = userRepository.getUser(currentUser.uid)
        _userAuthenticationState.value = when (user?.completeInputUserInfo) {
            true -> NavigationState.TO_MAIN
            false -> NavigationState.TO_SIGNUP
            else -> NavigationState.TO_LOGIN
        }
    }

    fun googleLogin(context: Context) = viewModelScope.launch {
        try {
            val googleCredential = authRepository.getGoogleCredential(context) ?: return@launch
            _isLoading.value = true
            val authResult = authRepository.signInWithGoogle(googleCredential)
            val authUser = authResult.user ?: return@launch
            handleUserAuthentication(context, authUser, "구글")
        } catch (e: FirebaseAuthUserCollisionException) {
            _isLoading.value = false
            Log.d("test1234", "구글 : ${e.message}")
            Toast.makeText(context, "이미 사용중인 계정이 존재합니다", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            _isLoading.value = false
            Log.d("test1234", "구글 : ${e.message}")
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun kakaoLogin(context: Context) = viewModelScope.launch {
        try {
            val kakaoAccount = authRepository.getKaKaoAccount(context) ?: return@launch
            _isLoading.value = true
            val authResult = authRepository.signInWithKaKao(kakaoAccount)
            val authUser = authResult.user ?: return@launch
            handleUserAuthentication(context, authUser, "카카오")
        } catch (e: FirebaseAuthUserCollisionException) {
            _isLoading.value = false
            Log.d("test1234", "카카오 : ${e.message}")
            Toast.makeText(context, "이미 사용중인 계정이 존재합니다", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            _isLoading.value = false
            Log.d("test1234", "카카오 : ${e.message}")
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun naverLogin(context: Context) = viewModelScope.launch {
        try {
            val naverAccessToken = authRepository.getNaverAccessToken(context) ?: return@launch
            _isLoading.value = true
            val naverCustomToken = authRepository.getNaverCustomToken(naverAccessToken) ?: return@launch
            val authResult = authRepository.signInWithNaver(naverCustomToken)
            val authUser = authResult?.user ?: return@launch
            handleUserAuthentication(context, authUser, "네이버")
        } catch (e: Exception) {
            _isLoading.value = false
            Log.d("test1234", "네이버 : ${e.message}")
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    private suspend fun handleUserAuthentication(context: Context, authUser: FirebaseUser, loginType: String) {
        try {
            val user = userRepository.getUser(authUser.uid)
            if (user == null) {
                val newUser = UserModel(
                    uid = authUser.uid,
                    idx = userRepository.getUserSequence()?.plus(1) ?: -1,
                    name = authUser.displayName ?: "",
                    loginType = loginType,
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
                userRepository.incrementUserSequence()
                _userAuthenticationState.value = NavigationState.TO_SIGNUP
            } else {
                _userAuthenticationState.value = when (user.completeInputUserInfo) {
                    true -> {
                        val apartment = App.apartmentRepository.getApartment(user.apartmentUid)
                        apartment?.let {
                            App.prefs.setLatitude(it.latitude)
                            App.prefs.setLongitude(it.longitude)
                        }
                        NavigationState.TO_MAIN
                    }
                    false -> NavigationState.TO_SIGNUP
                }
            }
            _isLoading.value = false
        } catch (e: Exception){
            _isLoading.value = false
            Log.d("test1234", "정보저장 오류 : ${e.message}")
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}