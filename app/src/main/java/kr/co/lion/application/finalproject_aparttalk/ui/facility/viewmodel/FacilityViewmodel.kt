package kr.co.lion.application.finalproject_aparttalk.ui.facility.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.model.FacilityModel
import kr.co.lion.application.finalproject_aparttalk.repository.FacilityRepository

class FacilityViewmodel : ViewModel(){

    private val facilityRepository = FacilityRepository()

    private val _facilityList = MutableLiveData<List<FacilityModel>>()
    var facilityInfo : LiveData<List<FacilityModel>> = _facilityList

    //facility 정보를 가져온다
    fun getFacilityInfoData() = viewModelScope.launch {
        Log.d("test1234", "1111")
        val facilityInfo = facilityRepository.getFacilityInfoData()
        Log.d("test1234", "2222")
        val facilityInfoList = mutableListOf<FacilityModel>()
        Log.d("test1234", "3333")

        facilityInfo.forEach {facilityModel ->
            val facilityImg = getFacilityInfoImage(facilityModel.imageRes)
            facilityModel.imageRes = facilityImg?: facilityModel.imageRes
            facilityInfoList.add(facilityModel)
        }

        Log.d("test1234", "4444")
        _facilityList.value = facilityInfoList
        Log.d("test1234", "5555")
    }


    //facility Image를 가져온다
    private suspend fun getFacilityInfoImage(image:String):String?{
        return facilityRepository.getFacilityImage(image)
    }

}