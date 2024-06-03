package kr.co.lion.application.finalproject_aparttalk.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.model.UserModel
import kr.co.lion.application.finalproject_aparttalk.repository.UserRepository

class SignUpViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _user = MutableLiveData<UserModel>()
    val user: LiveData<UserModel> get() = _user

    private var initializeUserData: UserModel? = null

    init {
        getUserInfo()
    }

    private fun getUserInfo() = viewModelScope.launch {
        val authUser = FirebaseAuth.getInstance().currentUser ?: return@launch
        _user.value = userRepository.getUser(authUser.uid)
        initializeUserData = _user.value?.copy()
    }

    // --- SignUp1 ---
    fun resetAgreeCheckBox(){
        if(_user.value != null && initializeUserData != null){
            _user.value!!.agreementCheck1 = initializeUserData!!.agreementCheck1
            _user.value!!.agreementCheck2 = initializeUserData!!.agreementCheck2
            _user.value!!.agreementCheck3 = initializeUserData!!.agreementCheck3
        }
    }

    fun setAgreeCheckBox(check1: Boolean, check2: Boolean, check3: Boolean){
        _user.value?.let {
            it.agreementCheck1 = check1
            it.agreementCheck2 = check2
            it.agreementCheck3 = check3
        }
    }

    // --- SignUp2 ---
    fun resetName(){
        if(_user.value != null && initializeUserData != null){
            _user.value!!.name = initializeUserData!!.name
        }
    }

    fun setName(name: String){
        _user.value?.let {
            it.name = name
        }
    }

    fun resetBirthDate(){
        if(_user.value != null && initializeUserData != null){
            _user.value!!.birthYear = initializeUserData!!.birthYear
            _user.value!!.birthMonth = initializeUserData!!.birthMonth
            _user.value!!.birthDay = initializeUserData!!.birthDay
        }
    }

    fun setBirthDate(year: Int?, month: Int?, day: Int?) {
        _user.value?.let {
            it.birthYear = year
            it.birthMonth = month
            it.birthDay = day
        }
    }

    // --- SignUp3 ---
    fun resetGender(){
        if(_user.value != null && initializeUserData != null){
            _user.value!!.gender = initializeUserData!!.gender
        }
    }

    fun setGender(gender: String) {
        _user.value?.let {
            it.gender = gender
        }
    }

    fun resetEmail(){
        if(_user.value != null && initializeUserData != null){
            _user.value!!.email = initializeUserData!!.email
        }
    }

    fun setEmail(email: String) {
        _user.value?.let {
            it.gender = email
        }
    }

    // --- SignUp4 ---
    fun resetApartInfo(){
        if(_user.value != null && initializeUserData != null){
            _user.value!!.apartmentUid = initializeUserData!!.apartmentUid
            _user.value!!.apartmentDong = initializeUserData!!.apartmentDong
            _user.value!!.apartmentHo = initializeUserData!!.apartmentHo
        }
    }

    fun resetApartDongHo(){
        if(_user.value != null && initializeUserData != null){
            _user.value!!.apartmentDong = initializeUserData!!.apartmentDong
            _user.value!!.apartmentHo = initializeUserData!!.apartmentHo
        }
    }

    fun setApartInfo(apartName: String, apartAddress: String) {
        _user.value?.let {
            it.apartmentUid = apartName
        }
    }

    fun setApartDongHo(dong: Int, ho: Int){
        _user.value?.let {
            it.apartmentDong = dong
            it.apartmentHo = ho
        }
    }

    fun resetApartCertification(){
        if(_user.value != null && initializeUserData != null){
            _user.value!!.apartCertification = initializeUserData!!.apartCertification
        }
    }
    fun setApartCertification(isCertification: Boolean){
        _user.value?.let {
            it.apartCertification = isCertification
        }
    }

    fun saveUserInfo() = viewModelScope.launch {

        _user.value?.let {
            val updateUser = mapOf<String, Any?>(
                "agreementCheck1" to it.agreementCheck1,
                "agreementCheck2" to it.agreementCheck2,
                "agreementCheck3" to it.agreementCheck3,
                "name" to it.name,
                "birthYear" to it.birthYear,
                "birthMonth" to it.birthMonth,
                "birthDay" to it.birthDay,
                "gender" to it.gender,
                "email" to it.email,
                "phoneNumber" to it.phoneNumber,
                "apartmentUid" to it.apartmentUid,
                "apartmentDong" to it.apartmentDong,
                "apartmentHo" to it.apartmentHo,
                "apartCertification" to it.apartCertification,
                "completeInputUserInfo" to true,
            )

            userRepository.updateUser(it.uid, updateUser)
        }
    }
}