package kr.co.lion.application.finalproject_aparttalk.ui.facility.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kr.co.lion.application.finalproject_aparttalk.model.FacilityModel
import kr.co.lion.application.finalproject_aparttalk.model.FacilityResModel
import kr.co.lion.application.finalproject_aparttalk.repository.FacilityResRepository
import java.util.Date
import kotlin.math.abs

class FacilityResInfoViewmodel : ViewModel() {

    private val facilityResRepository = FacilityResRepository()

    private val _facilityResList = MutableLiveData<List<FacilityResModel>>()
    val facilityList : LiveData<List<FacilityResModel>> = _facilityResList



    //예약 정보를 저장한다
    suspend fun insertResData(
        userUid:String, titleText:String, useTime:String, imageRes:String, usePrice:String, reservationState:Boolean, reservationData:String, userName:String, userNumber:String
        , callback:(Boolean) -> Unit
    ){
        val reserveTime = Timestamp.now()

        viewModelScope.launch {
            val facilityResData = FacilityResModel(userUid, titleText, useTime, imageRes, usePrice, reservationState, reservationData, userName, userNumber, reserveTime)
            val success = withContext(Dispatchers.IO){
                try {
                    facilityResRepository.insertResData(facilityResData)
                    true
                }catch (e:Exception){
                    false
                }
            }
            callback(success)

        }
    }


    //예약 정보를 userUid값으로 가져온다
    suspend fun getFacilityResData(userUid:String) {
        val facilityInfo = facilityResRepository.getFacilityInfoData(userUid)
        val facilityInfoList = mutableListOf<FacilityResModel>()

        facilityInfo.forEach { facilityInfoData ->
            facilityInfoList.add(facilityInfoData)

            _facilityResList.value = facilityInfoList

        }
    }

    //예약 제한
    fun checkFacilityRes():Boolean{
        val currentTime = Date()
        val oneDayInMillis:Long = 24 * 60 * 60 * 1000

        val facilityResData = facilityList.value?.get(0)?.reserveTime?.toDate()

        if (facilityResData != null){
            val timeDiff = abs(currentTime.time - facilityResData.time)
            return timeDiff >= oneDayInMillis
        }
        return false
    }



}





