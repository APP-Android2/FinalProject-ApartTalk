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

class FinancialSystemViewModel: ViewModel() {
    private val operationInfoRepository = OperationInfoRepository(OperationInfoDataSource())
    var allList = mutableListOf<OperationInfoModel>()
    var financialSystemList = mutableListOf<OperationInfoModel>()

    // 재무제표 글 리스트 작성자
    private val _textViewFinancialSystemWriter = MutableLiveData<String>()
    val textViewFinancialSystemWriter: LiveData<String> get() = _textViewFinancialSystemWriter

    // 재무제표 글 리스트 종류
    private val _textViewFinancialSystemType = MutableLiveData<String>()
    val textViewFinancialSystemType: LiveData<String> get() = _textViewFinancialSystemType

    // 재무제표 글 리스트 제목
    private val _textViewFinancialSystemSubject = MutableLiveData<String>()
    val textViewFinancialSystemSubject: LiveData<String> get() = _textViewFinancialSystemSubject

    // 재무제표 글 리스트 날짜
    private val _textViewFinancialSystemDate = MutableLiveData<String>()
    val textViewFinancialSystemDate: LiveData<String> get() = _textViewFinancialSystemDate

    // 게시글 목록을 가져온다.
    suspend fun gettingOperationInfoList(apartmentUid: String) : MutableList<OperationInfoModel> {
        return operationInfoRepository.gettingOperationInfoList(apartmentUid)
    }

    // 게시글 재무제표 리스트 받아오기.
    suspend fun gettingFinancialSystemList(apartmentUid: String) : MutableList<OperationInfoModel> {
        val job1 = CoroutineScope(Dispatchers.Main).launch {
            allList = gettingOperationInfoList(apartmentUid)
            financialSystemList.clear()
            allList.forEach {
                when(it.OperationInfoType) {
                    "FinancialSystem" -> financialSystemList.add(it)
                }
            }
        }
        job1.join()

        //Log.d("FinancialSystemViewModel", "Fetched Bidding Notices: $financialSystemList")
        return financialSystemList
    }
}