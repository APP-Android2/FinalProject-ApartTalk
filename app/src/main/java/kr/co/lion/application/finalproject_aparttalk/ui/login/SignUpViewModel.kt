package kr.co.lion.application.finalproject_aparttalk.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {

    private val _loginType = MutableLiveData<String>()
    val loginType: LiveData<String> get() = _loginType

    fun setLoginType(loginType: String){
        _loginType.value = loginType
    }

    // 회원 정보 입력 완료 여부
    private val _isCompleteGetUserInfo = MutableLiveData<Boolean>().apply { value = false }
    val isCompleteGetUserInfo: LiveData<Boolean> get() = _isCompleteGetUserInfo

    fun setIsCompleteGetUserInfo(isComplete: Boolean){
        _isCompleteGetUserInfo.value = isComplete
    }

    // --- SignUp1 ---
    var checkbox1Checked: Boolean = false
    var checkbox2Checked: Boolean = false
    var checkbox3Checked: Boolean = false

    // --- SignUp2 ---
    private val _name = MutableLiveData<String>()
    val name: LiveData<String> get() = _name

    fun initializeName(){
        _name.value = ""
    }

    fun setName(name: String){
        _name.value = name
    }

    private val _birthYear = MutableLiveData<Int?>()
    val birthYear: LiveData<Int?> get() = _birthYear

    private val _birthMonth = MutableLiveData<Int?>()
    val birthMonth: LiveData<Int?> get() = _birthMonth

    private val _birthDay = MutableLiveData<Int?>()
    val birthDay: LiveData<Int?> get() = _birthDay

    fun initializeBirthDate(){
        _birthYear.value = null
        _birthMonth.value = null
        _birthDay.value = null
    }

    fun setBirthDate(year: Int?, month: Int?, day: Int?) {
        _birthYear.value = year
        _birthMonth.value = month
        _birthDay.value = day
    }

    // --- SignUp3 ---
    private val _gender = MutableLiveData<String>().apply { value = "선택안함" }
    val gender: LiveData<String> get() = _gender

    fun initializeGender(){
        _gender.value = "선택안함"
    }

    fun setGender(gender: String) {
        _gender.value = gender
    }

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    fun initializeEmail(){
        _email.value = ""
    }

    fun setEmail(email: String) {
        _email.value = email
    }

    // --- SignUp4 ---
    private val _apartmentName = MutableLiveData<String>()
    val apartmentName: LiveData<String> get() = _apartmentName

    private val _apartmentAddress = MutableLiveData<String>()
    val apartmentAddress: LiveData<String> get() = _apartmentAddress

    private val _apartmentDong = MutableLiveData<Int?>()
    val apartmentDong: LiveData<Int?> get() = _apartmentDong

    private val _apartmentHo = MutableLiveData<Int?>()
    val apartmentHo: LiveData<Int?> get() = _apartmentHo

    fun initializeApartmentInfo(){
        _apartmentName.value = ""
        _apartmentAddress.value = ""
        _apartmentDong.value = null
        _apartmentHo.value = null
    }

    fun initializeApartmentDongHo(){
        _apartmentDong.value = null
        _apartmentHo.value = null
    }

    fun setApartmentInfo(apartmentName: String, apartmentAddress: String) {
        _apartmentName.value = apartmentName
        _apartmentAddress.value = apartmentAddress
    }

    fun setApartmentDongHo(dong: Int, ho: Int){
        _apartmentDong.value = dong
        _apartmentHo.value = ho
    }

    fun userAllInfo(){

        var s = "로그인 타입 : ${_loginType.value}\n" +
                "회원 정보 입력 완료 여부 : ${_isCompleteGetUserInfo.value}\n" +
                "체크1 : ${checkbox1Checked}\n" +
                "체크2 : ${checkbox2Checked}\n" +
                "체크3 : ${checkbox3Checked}\n" +
                "${_birthYear.value}년 ${_birthMonth.value}월 ${_birthDay.value}일\n" +
                "성별 : ${_gender.value}\n" +
                "이메일 : ${_email.value}\n" +
                "아파트 이름 : ${_apartmentName.value}\n" +
                "아파트 주소 : ${_apartmentAddress.value}\n" +
                "${_apartmentDong.value}동 ${_apartmentHo.value}호"

        Log.d("test1234", s)
    }
}