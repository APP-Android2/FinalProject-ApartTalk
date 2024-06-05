package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.db.local.LocalApartmentDataSource
import kr.co.lion.application.finalproject_aparttalk.model.OperationInfoModel
import kr.co.lion.application.finalproject_aparttalk.repository.OperationInfoRepository

class BiddingNoticeViewModel(private val operationInfoRepository: OperationInfoRepository, private val localApartmentDataSource: LocalApartmentDataSource): ViewModel() {
    var allList = mutableListOf<OperationInfoModel>()
    var biddingNoticeList = mutableListOf<OperationInfoModel>()

    // 운영정보 입찰공고 리스트 작성자
    private val _textViewWriterBiddingNotice = MutableLiveData<String>()
    val textViewWriterBiddingNotice: LiveData<String> get() = _textViewWriterBiddingNotice

    // 운영정보 입찰공고 리스트 타입
    private val _textViewTypeBiddingNotice = MutableLiveData<String>()
    val textViewTypeBiddingNotice: LiveData<String> get() = _textViewTypeBiddingNotice

    // 운영정보 입찰공고 리스트 제목
    private val _textViewSubjectBiddingNotice = MutableLiveData<String>()
    val textViewSubjectBiddingNotice: LiveData<String> get() = _textViewSubjectBiddingNotice

    // 운영정보 입찰공고 리스트 날짜
    private val _textViewDateBiddingNotice = MutableLiveData<String>()
    val textViewDateBiddingNotice: LiveData<String> get() = _textViewDateBiddingNotice

    // 입찰공고 글 목록을 가져온다.
    suspend fun gettingOperationInfoList() : MutableList<OperationInfoModel> {
        val apartment = localApartmentDataSource.getApartment()
        val apartmentUid = apartment?.uid ?: throw IllegalStateException("Apartment UID is missing")
        return operationInfoRepository.gettingOperationInfoList(apartmentUid)
    }

    // 입찰공고 리스트 받아오기.
    suspend fun gettingBiddingNoticeList() : MutableList<OperationInfoModel> {
        val job1 = CoroutineScope(Dispatchers.Main).launch {
            allList = gettingOperationInfoList()
            biddingNoticeList.clear()
            allList.forEach {
                when(it.OperationInfoType) {
                    "BiddingNotice" -> biddingNoticeList.add(it)
                }
            }
        }
        job1.join()

        return biddingNoticeList
    }
}