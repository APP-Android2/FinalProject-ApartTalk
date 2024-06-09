package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.AptSchedule

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.db.AptScheduleDataSource
import kr.co.lion.application.finalproject_aparttalk.model.AptScheduleModel
import kr.co.lion.application.finalproject_aparttalk.repository.AptScheduleRepository

class AptScheduleViewModel: ViewModel() {

    private val aptScheduleRepository = AptScheduleRepository(AptScheduleDataSource())
    var allDateList = mutableListOf<AptScheduleModel>()
    var selectedDateList = mutableListOf<AptScheduleModel>()

    // 아파트일정_날짜
    private val _textViewCalendarDate = MutableLiveData<String>()
    val textViewCalendarDate: LiveData<String> get() = _textViewCalendarDate

    // 아파트일정_시간
    private val _textViewCalendarTime = MutableLiveData<String>()
    val textViewCalendarTime: LiveData<String> get() = _textViewCalendarTime

    // 아파트일정_제목
    private val _textviewCalendarSubject = MutableLiveData<String>()
    val textviewCalendarSubject: LiveData<String> get() = _textviewCalendarSubject

    // 모든 일정 리스트를 가져온다.
    suspend fun gettingAllDateList(apartmentUid: String) : MutableList<AptScheduleModel> {
        return aptScheduleRepository.gettingAptScheduleList(apartmentUid)
    }

    // 선택된 날짜 일정 리스트를 받아오기.
    suspend fun gettingSelectedDateList(apartmentUid: String, date: String) : MutableList<AptScheduleModel> {
        val job1 = CoroutineScope(Dispatchers.Main).launch {
            allDateList = gettingAllDateList(apartmentUid)
            selectedDateList.clear()
            allDateList.forEach {
                if (it.aptScheduleDate == date) {
                    selectedDateList.add(it)
                }
            }
        }
        job1.join()

        Log.d("AptScheduleViewModel", "Fetched AptSchedule: $selectedDateList")
        return selectedDateList
    }
}