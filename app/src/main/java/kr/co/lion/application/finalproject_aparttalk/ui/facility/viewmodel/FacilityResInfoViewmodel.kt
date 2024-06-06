package kr.co.lion.application.finalproject_aparttalk.ui.facility.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kr.co.lion.application.finalproject_aparttalk.model.FacilityModel
import kr.co.lion.application.finalproject_aparttalk.model.FacilityResModel
import kr.co.lion.application.finalproject_aparttalk.repository.FacilityResRepository

class FacilityResInfoViewmodel : ViewModel() {

    private val facilityResRepository = FacilityResRepository()

    private val _facilityResList = MutableLiveData<List<FacilityResModel>>()
    var facilityList : LiveData<List<FacilityResModel>> = _facilityResList


    //예약 정보를 저장한다
    suspend fun insertResData(
        userUid:String, titleText:String, useTime:String, imageRes:String, usePrice:String, reservationState:Boolean, reservationData:String
        , callback:(Boolean) -> Unit
    ){
        viewModelScope.launch {
            val facilityResData = FacilityResModel(userUid, titleText, useTime, imageRes, usePrice, reservationState, reservationData)
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
    suspend fun getFacilityData(userUid:String):FacilityResModel?{
        return facilityResRepository.getFacilityInfoData(userUid)
    }



}





