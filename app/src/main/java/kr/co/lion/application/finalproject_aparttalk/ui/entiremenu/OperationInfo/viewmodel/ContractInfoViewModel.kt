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

class ContractInfoViewModel: ViewModel() {
    private val operationInfoRepository = OperationInfoRepository(OperationInfoDataSource())
    var allList = mutableListOf<OperationInfoModel>()
    var contractInfoList = mutableListOf<OperationInfoModel>()

    // 계약서정보 글 리스트 작성자
    private val _textViewContractInfoWriter = MutableLiveData<String>()
    val textViewContractInfoWriter: LiveData<String> get() = _textViewContractInfoWriter

    // 계약서정보 글 리스트 종류
    private val _textViewContractInfoType = MutableLiveData<String>()
    val textViewContractInfoType: LiveData<String> get() = _textViewContractInfoType

    // 계약서정보 글 리스트 제목
    private val _textViewContractInfoSubject = MutableLiveData<String>()
    val textViewContractInfoSubject: LiveData<String> get() = _textViewContractInfoSubject

    // 계약서정보 글 리스트 날짜
    private val _textViewContractInfoDate = MutableLiveData<String>()
    val textViewContractInfoDate: LiveData<String> get() = _textViewContractInfoDate

    // 게시글 목록을 가져온다.
    suspend fun gettingOperationInfoList(apartmentUid: String) : MutableList<OperationInfoModel> {
        return operationInfoRepository.gettingOperationInfoList(apartmentUid)
    }

    // 게시글 계약서정보 리스트 받아오기.
    suspend fun gettingContractInfoList(apartmentUid: String) : MutableList<OperationInfoModel> {
        val job1 = CoroutineScope(Dispatchers.Main).launch {
            allList = gettingOperationInfoList(apartmentUid)
            contractInfoList.clear()
            allList.forEach {
                when(it.OperationInfoType) {
                    "ContractInfo" -> contractInfoList.add(it)
                }
            }
        }
        job1.join()

        //Log.d("BiddingNoticeViewModel", "Fetched Bidding Notices: $biddingNoticeList")
        return contractInfoList
    }
}
