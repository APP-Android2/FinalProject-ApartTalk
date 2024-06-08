package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.db.local.LocalApartmentDataSource
import kr.co.lion.application.finalproject_aparttalk.model.OperationInfoModel
import kr.co.lion.application.finalproject_aparttalk.repository.OperationInfoRepository

class OperationInfoViewModel(
    private val operationInfoRepository: OperationInfoRepository,
    private val localApartmentDataSource: LocalApartmentDataSource
) : ViewModel() {

    private val _operationInfoData = MutableLiveData<OperationInfoModel?>()
    val operationInfoData: LiveData<OperationInfoModel?> get() = _operationInfoData

    private val _allList = MutableLiveData<List<OperationInfoModel>>()
    val allList: LiveData<List<OperationInfoModel>> get() = _allList

    private val _filteredList = MutableLiveData<List<OperationInfoModel>>()
    val filteredList: LiveData<List<OperationInfoModel>> get() = _filteredList

    fun selectOperationInfoData(operationInfoIdx: Int, apartmentUid: String) {
        viewModelScope.launch {
            _operationInfoData.value = operationInfoRepository.selectingOperationInfoData(operationInfoIdx, apartmentUid)
        }
    }

    fun getOperationInfoList() {
        viewModelScope.launch {
            try {
                val apartment = localApartmentDataSource.getApartment()
                val apartmentUid = apartment?.uid ?: throw IllegalStateException("Apartment UID is missing")
                _allList.value = operationInfoRepository.gettingOperationInfoList(apartmentUid)
            } catch (e: Exception) {
                _allList.value = emptyList()
            }
        }
    }

    fun filterOperationInfoList(type: String) {
        _allList.value?.let { list ->
            _filteredList.value = list.filter { it.OperationInfoType == type }
        }
    }
}
