package kr.co.lion.application.finalproject_aparttalk.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {

    // --- SignUp1 ---
    var checkbox1Checked: Boolean = false
    var checkbox2Checked: Boolean = false
    var checkbox3Checked: Boolean = false

    // --- SignUp2 ---
    private val _name = MutableLiveData<String>()
    val name: LiveData<String> get() = _name

    fun setName(name: String){
        _name.value = name
    }

    private val _birthYear = MutableLiveData<Int?>()
    val birthYear: LiveData<Int?> get() = _birthYear

    private val _birthMonth = MutableLiveData<Int?>()
    val birthMonth: LiveData<Int?> get() = _birthMonth

    private val _birthDay = MutableLiveData<Int?>()
    val birthDay: LiveData<Int?> get() = _birthDay

    fun setBirthDate(year: Int?, month: Int?, day: Int?) {
        _birthYear.value = year
        _birthMonth.value = month
        _birthDay.value = day
    }

    // --- SignUp3 ---
    private val _gender = MutableLiveData<String>().apply { value = "선택안함" }
    val gender: LiveData<String> get() = _gender

    fun setGender(gender: String) {
        _gender.value = gender
    }

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    fun setEmail(email: String) {
        _email.value = email
    }

    // --- SignUp4 ---
    var apartmentName: String = ""
    var apartmentAddress: String = ""
}