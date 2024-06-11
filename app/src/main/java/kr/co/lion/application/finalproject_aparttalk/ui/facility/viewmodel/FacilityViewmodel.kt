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
        val facilityInfo = facilityRepository.getFacilityInfoData()
        val facilityInfoList = mutableListOf<FacilityModel>()

        facilityInfo.forEach {facilityModel ->
            val facilityImg = getFacilityInfoImage(facilityModel.imageRes)
            facilityModel.imageRes = facilityImg?: facilityModel.imageRes
            facilityInfoList.add(facilityModel)
        }
        _facilityList.value = facilityInfoList
    }


    //facility Image를 가져온다
    private suspend fun getFacilityInfoImage(image:String):String?{
        return facilityRepository.getFacilityImage(image)
    }

}