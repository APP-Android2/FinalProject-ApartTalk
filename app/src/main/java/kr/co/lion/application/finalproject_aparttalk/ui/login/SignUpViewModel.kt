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
    private val _isCompleteInputUserInfo = MutableLiveData<Boolean>().apply { value = false }
    val isCompleteInputUserInfo: LiveData<Boolean> get() = _isCompleteInputUserInfo

    fun setIsCompleteInputUserInfo(isComplete: Boolean){
        _isCompleteInputUserInfo.value = isComplete
    }

    // 회원 아파트 인증 여부
    private val _isApartCertification = MutableLiveData<Boolean>().apply { value = false }
    val isApartCertification: LiveData<Boolean> get() = _isApartCertification

    fun initializeIsApartCertification(){
        _isApartCertification.value = false
    }
    fun setIsApartCertification(isCertification: Boolean){
        _isApartCertification.value = isCertification
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
    private val _apartName = MutableLiveData<String>()
    val apartName: LiveData<String> get() = _apartName

    private val _apartAddress = MutableLiveData<String>()
    val apartAddress: LiveData<String> get() = _apartAddress

    private val _apartDong = MutableLiveData<Int?>()
    val apartDong: LiveData<Int?> get() = _apartDong

    private val _apartHo = MutableLiveData<Int?>()
    val apartHo: LiveData<Int?> get() = _apartHo

    fun initializeApartInfo(){
        _apartName.value = ""
        _apartAddress.value = ""
        _apartDong.value = null
        _apartHo.value = null
    }

    fun initializeApartDongHo(){
        _apartDong.value = null
        _apartHo.value = null
    }

    fun setApartInfo(apartName: String, apartAddress: String) {
        _apartName.value = apartName
        _apartAddress.value = apartAddress
    }

    fun setApartDongHo(dong: Int, ho: Int){
        _apartDong.value = dong
        _apartHo.value = ho
    }

    fun userAllInfo(){

        var s = "로그인 타입 : ${_loginType.value}\n" +
                "회원 정보 입력 완료 여부 : ${_isCompleteInputUserInfo.value}\n" +
                "회원 아파트 인증 여부 : ${_isApartCertification.value}\n" +
                "체크1 : ${checkbox1Checked}\n" +
                "체크2 : ${checkbox2Checked}\n" +
                "체크3 : ${checkbox3Checked}\n" +
                "생년월일 : ${_birthYear.value}년 ${_birthMonth.value}월 ${_birthDay.value}일\n" +
                "성별 : ${_gender.value}\n" +
                "이메일 : ${_email.value}\n" +
                "아파트 이름 : ${_apartName.value}\n" +
                "아파트 주소 : ${_apartAddress.value}\n" +
                "${_apartDong.value}동 ${_apartHo.value}호"

        Log.d("test1234", s)
    }
}