package kr.co.lion.application.finalproject_aparttalk.ui.login.viewmodel

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.App
import kr.co.lion.application.finalproject_aparttalk.model.UserModel
import kr.co.lion.application.finalproject_aparttalk.repository.ApartmentRepository
import kr.co.lion.application.finalproject_aparttalk.repository.AuthRepository
import kr.co.lion.application.finalproject_aparttalk.repository.UserRepository
import kr.co.lion.application.finalproject_aparttalk.ui.login.NavigationState
import java.util.concurrent.TimeUnit

class LoginViewModel(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
    private val apartmentRepository: ApartmentRepository,
) : ViewModel() {

    private val _userAuthenticationState = MutableLiveData<NavigationState>().apply {
        value = NavigationState.TO_LOGIN
    }
    val userAuthenticationState: LiveData<NavigationState> = _userAuthenticationState

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _verificationId = MutableLiveData<String?>()
    val verificationId: LiveData<String?> = _verificationId

    private val _verificationTime = MutableLiveData<Boolean>().apply { value = false }
    val verificationTime: LiveData<Boolean> = _verificationTime

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
        if (user == null){
            _userAuthenticationState.value = NavigationState.TO_LOGIN
            return@launch
        }
        _userAuthenticationState.value = when (user.completeInputUserInfo) {
            true -> {
                apartmentRepository.getApartment(user.apartmentUid)
                NavigationState.TO_MAIN
            }
            false -> NavigationState.TO_SIGNUP
        }
    }

    fun googleLogin(context: Context) = viewModelScope.launch {
        try {
            val googleCredential = authRepository.getGoogleCredential(context) ?: return@launch
            _isLoading.value = true
            val authResult = authRepository.signInWithGoogle(googleCredential)
            val authUser = authResult.user
            if(authUser == null){
                _isLoading.value = false
                return@launch
            }
            handleUserAuthentication(context, authUser, "구글")
        } catch (e: FirebaseAuthUserCollisionException) {
            _isLoading.value = false
            Log.d("test1234", "구글 : ${e.message}")
            Toast.makeText(context, "이미 가입된 계정이 존재합니다", Toast.LENGTH_SHORT).show()
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
            val authUser = authResult.user
            if(authUser == null){
                _isLoading.value = false
                return@launch
            }
            handleUserAuthentication(context, authUser, "카카오")
        } catch (e: FirebaseAuthUserCollisionException) {
            _isLoading.value = false
            Log.d("test1234", "카카오 : ${e.message}")
            Toast.makeText(context, "이미 가입된 계정이 존재합니다", Toast.LENGTH_SHORT).show()
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
            val naverCustomToken = authRepository.getNaverCustomToken(naverAccessToken)
            if (naverCustomToken == null){
                _isLoading.value = false
                return@launch
            }
            if (naverCustomToken == "409"){
                _isLoading.value = false
                Toast.makeText(context, "이미 가입된 계정이 존재합니다", Toast.LENGTH_SHORT).show()
                return@launch
            }
            val authResult = authRepository.signInWithNaver(naverCustomToken)
            val authUser = authResult?.user
            if (authUser == null){
                _isLoading.value = false
                return@launch
            }
            handleUserAuthentication(context, authUser, "네이버")
        } catch (e: Exception) {
            _isLoading.value = false
            Log.d("test1234", "네이버 : ${e.message}")
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun phoneLogin(activity: Activity, context: Context, phoneNumber: String) = viewModelScope.launch {
        try {
            val options = PhoneAuthOptions.newBuilder(FirebaseAuth.getInstance())
                .setPhoneNumber(phoneNumber.replace("010","+8210"))
                .setTimeout(120L, TimeUnit.SECONDS)
                .setActivity(activity)
                .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    override fun onVerificationCompleted(credential: PhoneAuthCredential) {}
                    override fun onVerificationFailed(e: FirebaseException) {}
                    override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                        _verificationId.value = verificationId
                    }
                })
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)
        } catch (e: FirebaseException) {
            Log.d("test1234", "Verification failed: ${e.message}")
        } catch (e: Exception) {
            Log.d("test1234", "Verification failed: ${e.message}")
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    suspend fun verifyCode(context: Context, code: String): Boolean {
        return try {
            _isLoading.value = true
            if (_verificationId.value == null) {
                Toast.makeText(context, "인증 ID가 없습니다.\n다시 시도해주세요.", Toast.LENGTH_SHORT).show()
                _isLoading.value = false
                false
            } else {
                val authResult = authRepository.signInWithPhone(_verificationId.value, code)
                val authUser = authResult.user
                if (authUser == null) {
                    _isLoading.value = false
                    false
                } else {
                    handleUserAuthentication(context, authUser, "휴대폰")
                    true
                }
            }
        } catch (e: Exception) {
            _isLoading.value = false
            Log.d("test1234", "휴대폰 : ${e.message}")
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            false
        }
    }

    fun resetVerificationId() { _verificationId.value = null }

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
                        val apartment = apartmentRepository.getApartment(user.apartmentUid)
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
        } catch (e: Exception) {
            _isLoading.value = false
            Log.d("test1234", "정보저장 오류 : ${e.message}")
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}