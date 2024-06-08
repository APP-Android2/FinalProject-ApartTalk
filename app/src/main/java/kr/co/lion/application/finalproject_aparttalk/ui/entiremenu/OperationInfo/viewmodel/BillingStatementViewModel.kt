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

class BillingStatementViewModel: ViewModel() {
    private val operationInfoRepository = OperationInfoRepository(OperationInfoDataSource())
    var allList = mutableListOf<OperationInfoModel>()
    var billingStatementList = mutableListOf<OperationInfoModel>()

    // 부과명세서 글 리스트 작성자
    private val _textViewBillingStatementWriter = MutableLiveData<String>()
    val textViewBillingStatementWriter: LiveData<String> get() = _textViewBillingStatementWriter

    // 부과명세서 글 리스트 종류
    private val _textViewBillingStatementType = MutableLiveData<String>()
    val textViewBillingStatementType: LiveData<String> get() = _textViewBillingStatementType

    // 부과명세서 글 리스트 제목
    private val _textViewBillingStatementSubject = MutableLiveData<String>()
    val textViewBillingStatementSubject: LiveData<String> get() = _textViewBillingStatementSubject

    // 부과명세서 글 리스트 날짜
    private val _textViewBillingStatementDate = MutableLiveData<String>()
    val textViewBillingStatementDate: LiveData<String> get() = _textViewBillingStatementDate

    // 게시글 목록을 가져온다.
    suspend fun gettingOperationInfoList(apartmentUid: String) : MutableList<OperationInfoModel> {
        return operationInfoRepository.gettingOperationInfoList(apartmentUid)
    }

    // 게시글 입찰공고 리스트 받아오기.
    suspend fun gettingBillingStatementList(apartmentUid: String) : MutableList<OperationInfoModel> {
        val job1 = CoroutineScope(Dispatchers.Main).launch {
            allList = gettingOperationInfoList(apartmentUid)
            billingStatementList.clear()
            allList.forEach {
                when(it.OperationInfoType) {
                    "BillingStatement" -> billingStatementList.add(it)
                }
            }
        }
        job1.join()

        //Log.d("BiddingNoticeViewModel", "Fetched Bidding Notices: $biddingNoticeList")
        return billingStatementList
    }
}