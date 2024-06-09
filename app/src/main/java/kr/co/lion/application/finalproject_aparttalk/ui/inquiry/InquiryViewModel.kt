package kr.co.lion.application.finalproject_aparttalk.ui.inquiry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.model.ApartmentModel
import kr.co.lion.application.finalproject_aparttalk.model.InquiryModel
import kr.co.lion.application.finalproject_aparttalk.model.UserModel
import kr.co.lion.application.finalproject_aparttalk.repository.InquiryRepository

class InquiryViewModel(private val repository: InquiryRepository) : ViewModel() {

    private val _userModel = MutableLiveData<UserModel?>()
    val userModel: LiveData<UserModel?> get() = _userModel

    private val _selectedInquiryModel = MutableLiveData<InquiryModel?>()
    val selectedInquiryModel: LiveData<InquiryModel?> get() = _selectedInquiryModel

    fun setUserModel(user: UserModel) {
        _userModel.value = user
    }

    fun setSelectedInquiryModel(inquiry: InquiryModel) {
        _selectedInquiryModel.value = inquiry
    }

    fun createInquiry(inquiry: InquiryModel) {
        viewModelScope.launch {
            repository.createInquiry(inquiry)
            // 새로운 문의 작성 후 다시 문의 목록을 불러옵니다.
            _userModel.value?.let { user ->
                getPendingInquiries(user.apartmentUid) { inquiries ->
                    _selectedInquiryModel.value = inquiries.firstOrNull { it.inquiryTitle == inquiry.inquiryTitle }
                }
            }
        }
    }

    fun getInquiries(apartmentUid: String, callback: (List<InquiryModel>) -> Unit) {
        viewModelScope.launch {
            val inquiries = repository.getInquiries(apartmentUid)
            callback(inquiries)
        }
    }

    fun getPendingInquiries(apartmentUid: String, callback: (List<InquiryModel>) -> Unit) {
        viewModelScope.launch {
            val inquiries = repository.getInquiries(apartmentUid).filter { !it.inquiryState }
            callback(inquiries)
        }
    }

    fun getCompletedInquiries(apartmentUid: String, callback: (List<InquiryModel>) -> Unit) {
        viewModelScope.launch {
            val inquiries = repository.getInquiries(apartmentUid).filter { it.inquiryState }
            callback(inquiries)
        }
    }

    fun getUser(uid: String, callback: (UserModel?) -> Unit) {
        viewModelScope.launch {
            val user = repository.getUser(uid)
            _userModel.postValue(user)
            callback(user)
        }
    }

    fun getApartment(uid: String, callback: (ApartmentModel?) -> Unit) {
        viewModelScope.launch {
            val apartment = repository.getApartment(uid)
            callback(apartment)
        }
    }
}
