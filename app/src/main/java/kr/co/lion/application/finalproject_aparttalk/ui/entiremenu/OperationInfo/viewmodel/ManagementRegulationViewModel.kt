package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.db.OperationInfoDataSource
import kr.co.lion.application.finalproject_aparttalk.model.OperationInfoModel
import kr.co.lion.application.finalproject_aparttalk.repository.OperationInfoRepository

class ManagementRegulationViewModel: ViewModel() {
    private val operationInfoRepository = OperationInfoRepository(OperationInfoDataSource())
    var allList = mutableListOf<OperationInfoModel>()
    var managementRegulationList = mutableListOf<OperationInfoModel>()

    // 관리규약 글 리스트 작성자
    private val _textViewManagementRegulationWriter = MutableLiveData<String>()
    val textViewManagementRegulationWriter: LiveData<String> get() = _textViewManagementRegulationWriter

    // 관리규약 글 리스트 종류
    private val _textViewManagementRegulationType = MutableLiveData<String>()
    val textViewManagementRegulationType: LiveData<String> get() = _textViewManagementRegulationType

    // 관리규약 글 리스트 제목
    private val _textViewManagementRegulationSubject = MutableLiveData<String>()
    val textViewManagementRegulationSubject: LiveData<String> get() = _textViewManagementRegulationSubject

    // 관리규약 글 리스트 날짜
    private val _textViewManagementRegulationDate = MutableLiveData<String>()
    val textViewManagementRegulationDate: LiveData<String> get() = _textViewManagementRegulationDate

    // 게시글 목록을 가져온다.
    suspend fun gettingOperationInfoList(apartmentUid: String) : MutableList<OperationInfoModel> {
        return operationInfoRepository.gettingOperationInfoList(apartmentUid)
    }

    // 게시글 관리규약 리스트 받아오기.
    suspend fun gettingManagementRegulationList(apartmentUid: String) : MutableList<OperationInfoModel> {
        val job1 = CoroutineScope(Dispatchers.Main).launch {
            allList = gettingOperationInfoList(apartmentUid)
            managementRegulationList.clear()
            allList.forEach {
                when(it.OperationInfoType) {
                    "ManagementRegulation" -> managementRegulationList.add(it)
                }
            }
        }
        job1.join()

        //Log.d("ManagementRegulationViewModel", "Fetched Bidding Notices: $managementRegulationList")
        return managementRegulationList
    }
}