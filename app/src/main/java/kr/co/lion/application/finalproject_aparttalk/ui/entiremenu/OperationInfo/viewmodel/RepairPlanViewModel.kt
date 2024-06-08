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

class RepairPlanViewModel: ViewModel() {
    private val operationInfoRepository = OperationInfoRepository(OperationInfoDataSource())
    var allList = mutableListOf<OperationInfoModel>()
    var repairPlanList = mutableListOf<OperationInfoModel>()

    // 수선계획 글 리스트 작성자
    private val _textViewRepairPlanWriter = MutableLiveData<String>()
    val textViewRepairPlanWriter: LiveData<String> get() = _textViewRepairPlanWriter

    // 수선계획 글 리스트 종류
    private val _textViewRepairPlanType = MutableLiveData<String>()
    val textViewRepairPlanType: LiveData<String> get() = _textViewRepairPlanType

    // 수선계획 글 리스트 제목
    private val _textViewRepairPlanSubject = MutableLiveData<String>()
    val textViewRepairPlanSubject: LiveData<String> get() = _textViewRepairPlanSubject

    // 수선계획 글 리스트 날짜
    private val _textViewRepairPlanDate = MutableLiveData<String>()
    val textViewRepairPlanDate: LiveData<String> get() = _textViewRepairPlanDate

    // 게시글 목록을 가져온다.
    suspend fun gettingOperationInfoList(apartmentUid: String) : MutableList<OperationInfoModel> {
        return operationInfoRepository.gettingOperationInfoList(apartmentUid)
    }

    // 게시글 수선계획 리스트 받아오기.
    suspend fun gettingRepairPlanList(apartmentUid: String) : MutableList<OperationInfoModel> {
        val job1 = CoroutineScope(Dispatchers.Main).launch {
            allList = gettingOperationInfoList(apartmentUid)
            repairPlanList.clear()
            allList.forEach {
                when(it.OperationInfoType) {
                    "RepairPlan" -> repairPlanList.add(it)
                }
            }
        }
        job1.join()

        //Log.d("RepairPlanViewModel", "Fetched Bidding Notices: $repairPlanList")
        return repairPlanList
    }
}