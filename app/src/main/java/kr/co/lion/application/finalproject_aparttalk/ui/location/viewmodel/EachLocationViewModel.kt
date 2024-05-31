package kr.co.lion.application.finalproject_aparttalk.ui.location.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.model.Place
import kr.co.lion.application.finalproject_aparttalk.repository.LocationRepository

class EachLocationViewModel : ViewModel() {

    private val locationRepository = LocationRepository()

    private val _eachLocationList = MutableLiveData<List<Place>>()
    val eachLocationList: LiveData<List<Place>> get() = _eachLocationList

    suspend fun searchEachLocationPlace(category:String, x:String, y:String, radius:Int){
        viewModelScope.launch {
            try {
                val response = locationRepository.getSearchFacility(category, x, y, radius)
                _eachLocationList.value = response.documents
            }catch (e:Exception){

            }
        }
    }
}