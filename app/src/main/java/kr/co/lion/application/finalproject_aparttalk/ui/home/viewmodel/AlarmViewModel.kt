package kr.co.lion.application.finalproject_aparttalk.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.co.lion.application.finalproject_aparttalk.model.PostData
import kr.co.lion.application.finalproject_aparttalk.repository.CommunityPostRepository

class AlarmViewModel : ViewModel() {

    private val communityPostRepository = CommunityPostRepository()

    private val _alarmList = MutableLiveData<List<PostData>>()
    var alarmList : LiveData<List<PostData>> = _alarmList


    //community의 모든 데이터를 가져온다
    suspend fun getAlarmInfo(apartUid:String){
        val alarmInfo = communityPostRepository.gettingCommunityPostList(apartUid)
        val alarmList = mutableListOf<PostData>()

        alarmInfo.forEach {
            alarmList.add(it)
        }

        _alarmList.value = alarmList
    }

}