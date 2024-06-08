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

class SafetyManagementViewModel: ViewModel() {
    private val operationInfoRepository = OperationInfoRepository(OperationInfoDataSource())
    var allList = mutableListOf<OperationInfoModel>()
    var safetyManagementList = mutableListOf<OperationInfoModel>()

    // 안전관리계획 글 리스트 작성자
    private val _textViewSafetyManagementWriter = MutableLiveData<String>()
    val textViewSafetyManagementWriter: LiveData<String> get() = _textViewSafetyManagementWriter

    // 안전관리계획 글 리스트 종류
    private val _textViewSafetyManagementType = MutableLiveData<String>()
    val textViewSafetyManagementType: LiveData<String> get() = _textViewSafetyManagementType

    // 안전관리계획 글 리스트 제목
    private val _textViewSafetyManagementSubject = MutableLiveData<String>()
    val textViewSafetyManagementSubject: LiveData<String> get() = _textViewSafetyManagementSubject

    // 안전관리계획 글 리스트 날짜
    private val _textViewSafetyManagementDate = MutableLiveData<String>()
    val textViewSafetyManagementDate: LiveData<String> get() = _textViewSafetyManagementDate

    // 게시글 목록을 가져온다.
    suspend fun gettingOperationInfoList(apartmentUid: String) : MutableList<OperationInfoModel> {
        return operationInfoRepository.gettingOperationInfoList(apartmentUid)
    }

    // 게시글 안전관리계획 리스트 받아오기.
    suspend fun gettingSafetyManagementList(apartmentUid: String) : MutableList<OperationInfoModel> {
        val job1 = CoroutineScope(Dispatchers.Main).launch {
            allList = gettingOperationInfoList(apartmentUid)
            safetyManagementList.clear()
            allList.forEach {
                when(it.OperationInfoType) {
                    "SafetyManagement" -> safetyManagementList.add(it)
                }
            }
        }
        job1.join()

        //Log.d("SafetyManagementViewModel", "Fetched Bidding Notices: $safetyManagementList")
        return safetyManagementList
    }
}