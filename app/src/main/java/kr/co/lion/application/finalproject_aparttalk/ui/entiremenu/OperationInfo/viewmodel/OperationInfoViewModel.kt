package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.viewmodel

import android.media.VolumeShaper.Operation
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

    // 특정 OperationInfo를 가져온다
    fun selectOperationInfoData(operationInfoIdx: Int, apartmentUid: String) {
        viewModelScope.launch {
            _operationInfoData.value = operationInfoRepository.selectingOperationInfoData(operationInfoIdx, apartmentUid)
        }
    }

    // 운영정보 목록을 가져온댜.
    fun getOperationInfoList() {
        viewModelScope.launch {
            val apartment = localApartmentDataSource.getApartment()
            val apartmentUid = apartment?.uid ?: throw IllegalStateException("Apartment UID is missing")
            _allList.value = operationInfoRepository.gettingOperationInfoList(apartmentUid)
        }
    }

    // 특정 타입의 글 목록을 필터링하여 가져온다
    fun filterOperationInfoList(type: String) {
        _allList.value?.let { list ->
            _filteredList.value = list.filter { it.OperationInfoType == type }
        }
    }
}