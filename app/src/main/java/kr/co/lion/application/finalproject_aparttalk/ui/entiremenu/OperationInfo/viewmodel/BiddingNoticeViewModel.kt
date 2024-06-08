package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.db.OperationInfoDataSource
import kr.co.lion.application.finalproject_aparttalk.model.OperationInfoModel
import kr.co.lion.application.finalproject_aparttalk.repository.OperationInfoRepository

class BiddingNoticeViewModel: ViewModel() {
    private val operationInfoRepository = OperationInfoRepository(OperationInfoDataSource())
    var allList = mutableListOf<OperationInfoModel>()
    var biddingNoticeList = mutableListOf<OperationInfoModel>()

    // 입찰공고 글 리스트 작성자
    private val _textViewBiddingNoticeWriter = MutableLiveData<String>()
    val textViewBiddingNoticeWriter: LiveData<String> get() = _textViewBiddingNoticeWriter

    // 입찰공고 글 리스트 종류
    private val _textViewBiddingNoticeType = MutableLiveData<String>()
    val textViewBiddingNoticeType: LiveData<String> get() = _textViewBiddingNoticeType

    // 입찰공고 글 리스트 제목
    private val _textViewBiddingNoticeSubject = MutableLiveData<String>()
    val textViewBiddingNoticeSubject: LiveData<String> get() = _textViewBiddingNoticeSubject

    // 입찰공고 글 리스트 날짜
    private val _textViewBiddingNoticeDate = MutableLiveData<String>()
    val textViewBiddingNoticeDate: LiveData<String> get() = _textViewBiddingNoticeDate

    // 게시글 목록을 가져온다.
    suspend fun gettingOperationInfoList(apartmentUid: String) : MutableList<OperationInfoModel> {
        return operationInfoRepository.gettingOperationInfoList(apartmentUid)
    }

    // 게시글 입찰공고 리스트 받아오기.
    suspend fun gettingBiddingNoticeList(apartmentUid: String) : MutableList<OperationInfoModel> {
        val job1 = CoroutineScope(Dispatchers.Main).launch {
            allList = gettingOperationInfoList(apartmentUid)
            biddingNoticeList.clear()
            allList.forEach {
                when(it.OperationInfoType) {
                    "BiddingNotice" -> biddingNoticeList.add(it)
                }
            }
        }
        job1.join()

        //Log.d("BiddingNoticeViewModel", "Fetched Bidding Notices: $biddingNoticeList")
        return biddingNoticeList
    }
}