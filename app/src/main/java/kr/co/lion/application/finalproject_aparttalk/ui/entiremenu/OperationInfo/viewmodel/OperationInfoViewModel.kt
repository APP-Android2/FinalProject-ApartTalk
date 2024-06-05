package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.model.OperationInfoModel
import kr.co.lion.application.finalproject_aparttalk.repository.OperationInfoRepository

class OperationInfoViewModel(private val repository: OperationInfoRepository) : ViewModel() {

    private val _operationInfoData = MutableLiveData<List<OperationInfoModel>>()

    val operationInfoData: LiveData<List<OperationInfoModel>> get() = _operationInfoData

    fun fetchOperationInfoData() {
        viewModelScope.launch {
            val data = repository.getOperationInfoData()
            _operationInfoData.value = data
        }
    }
}