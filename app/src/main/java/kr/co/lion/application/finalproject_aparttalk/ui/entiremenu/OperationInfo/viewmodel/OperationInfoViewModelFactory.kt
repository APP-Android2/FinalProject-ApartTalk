package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.co.lion.application.finalproject_aparttalk.db.local.LocalApartmentDataSource
import kr.co.lion.application.finalproject_aparttalk.repository.OperationInfoRepository

class OperationInfoViewModelFactory(
    private val operationInfoRepository: OperationInfoRepository,
    private val localApartmentDataSource: LocalApartmentDataSource
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OperationInfoViewModel::class.java)) {
            return OperationInfoViewModel(operationInfoRepository, localApartmentDataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}