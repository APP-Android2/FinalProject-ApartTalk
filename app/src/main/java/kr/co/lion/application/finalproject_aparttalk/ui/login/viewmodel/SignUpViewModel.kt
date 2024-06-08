package kr.co.lion.application.finalproject_aparttalk.ui.login.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.App
import kr.co.lion.application.finalproject_aparttalk.model.ApartmentModel
import kr.co.lion.application.finalproject_aparttalk.model.UserModel
import kr.co.lion.application.finalproject_aparttalk.repository.ApartmentRepository
import kr.co.lion.application.finalproject_aparttalk.repository.UserRepository

class SignUpViewModel(
    private val userRepository: UserRepository,
    private val apartmentRepository: ApartmentRepository
) : ViewModel() {

    private val _user = MutableLiveData<UserModel>()
    val user: LiveData<UserModel> get() = _user

    private var initializeUserData: UserModel? = null

    private val _apartmentList = MutableLiveData<List<ApartmentModel>>()
    val apartmentList: MutableLiveData<List<ApartmentModel>> get() = _apartmentList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        getUserInfo()
        getApartmentList()
    }

    private fun getApartmentList() = viewModelScope.launch {
        _apartmentList.value = apartmentRepository.getApartmentList()
    }

    private fun getUserInfo() = viewModelScope.launch {
        val authUser = FirebaseAuth.getInstance().currentUser ?: return@launch
        _user.value = userRepository.getUser(authUser.uid)
        initializeUserData = _user.value?.copy()
    }

    // --- SignUp1 ---
    fun resetAgreeCheckBox() {
        if (_user.value != null && initializeUserData != null) {
            _user.value!!.agreementCheck1 = initializeUserData!!.agreementCheck1
            _user.value!!.agreementCheck2 = initializeUserData!!.agreementCheck2
            _user.value!!.agreementCheck3 = initializeUserData!!.agreementCheck3
        }
    }

    fun setAgreeCheckBox(check1: Boolean, check2: Boolean, check3: Boolean) {
        _user.value?.let {
            it.agreementCheck1 = check1
            it.agreementCheck2 = check2
            it.agreementCheck3 = check3
        }
    }

    // --- SignUp2 ---
    fun resetName() {
        if (_user.value != null && initializeUserData != null) {
            _user.value!!.name = initializeUserData!!.name
        }
    }

    fun setName(name: String) {
        _user.value?.let {
            it.name = name
        }
    }

    fun resetBirthDate() {
        if (_user.value != null && initializeUserData != null) {
            _user.value!!.birthYear = initializeUserData!!.birthYear
            _user.value!!.birthMonth = initializeUserData!!.birthMonth
            _user.value!!.birthDay = initializeUserData!!.birthDay
        }
    }

    fun setBirthDate(year: Int, month: Int, day: Int) {
        _user.value?.let {
            it.birthYear = year
            it.birthMonth = month
            it.birthDay = day
        }
    }

    // --- SignUp3 ---
    fun resetGender() {
        if (_user.value != null && initializeUserData != null) {
            _user.value!!.gender = initializeUserData!!.gender
        }
    }

    fun setGender(gender: String) {
        _user.value?.let {
            it.gender = gender
        }
    }

    fun resetEmail() {
        if (_user.value != null && initializeUserData != null) {
            _user.value!!.email = initializeUserData!!.email
        }
    }

    fun setEmail(email: String) {
        _user.value?.let {
            it.email = email
        }
    }

    // --- SignUp4 ---
    fun resetApartInfo() {
        if (_user.value != null && initializeUserData != null) {
            _user.value!!.apartmentUid = initializeUserData!!.apartmentUid
            _user.value!!.apartmentDong = initializeUserData!!.apartmentDong
            _user.value!!.apartmentHo = initializeUserData!!.apartmentHo
        }
    }

    fun resetApartDongHo() {
        if (_user.value != null && initializeUserData != null) {
            _user.value!!.apartmentDong = initializeUserData!!.apartmentDong
            _user.value!!.apartmentHo = initializeUserData!!.apartmentHo
        }
    }

    fun setApartInfo(apartmentUid: String) {
        _user.value?.let {
            it.apartmentUid = apartmentUid
        }
    }

    fun setApartDongHo(dong: Int, ho: Int) {
        _user.value?.let {
            it.apartmentDong = dong
            it.apartmentHo = ho
        }
    }

    fun resetApartCertification() {
        if (_user.value != null && initializeUserData != null) {
            _user.value!!.apartCertification = initializeUserData!!.apartCertification
        }
    }

    fun setApartCertification(isCertification: Boolean) {
        _user.value?.let {
            it.apartCertification = isCertification
        }
    }

    fun saveUserInfo(context: Context) = viewModelScope.launch {
        _isLoading.value = true
        _user.value?.let { user ->
            val updateUser = mapOf<String, Any?>(
                "agreementCheck1" to user.agreementCheck1,
                "agreementCheck2" to user.agreementCheck2,
                "agreementCheck3" to user.agreementCheck3,
                "name" to user.name,
                "birthYear" to user.birthYear,
                "birthMonth" to user.birthMonth,
                "birthDay" to user.birthDay,
                "gender" to user.gender,
                "email" to user.email,
                "phoneNumber" to user.phoneNumber,
                "apartmentUid" to user.apartmentUid,
                "apartmentDong" to user.apartmentDong,
                "apartmentHo" to user.apartmentHo,
                "apartCertification" to user.apartCertification,
                "completeInputUserInfo" to true,
            )
            try {
                userRepository.updateUser(user.uid, updateUser)
                val apartment = apartmentList.value?.find { it.uid == user.apartmentUid }
                apartment?.let {
                    apartmentRepository.saveApartment(it)
                    App.prefs.setLatitude(apartment.latitude)
                    App.prefs.setLongitude(apartment.longitude)
                }
            } catch (e: Exception) {
                _isLoading.value = false
                Log.e("test1234", "Error updating user info", e)
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
        }
        _isLoading.value = false
    }
}