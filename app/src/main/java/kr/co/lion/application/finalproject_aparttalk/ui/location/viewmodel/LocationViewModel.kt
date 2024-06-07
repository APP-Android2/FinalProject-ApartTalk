package kr.co.lion.application.finalproject_aparttalk.ui.location.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.model.Place
import kr.co.lion.application.finalproject_aparttalk.model.ResultKeyword
import kr.co.lion.application.finalproject_aparttalk.repository.LocationRepository

class LocationViewModel : ViewModel() {
    private val locationRepository = LocationRepository()

    private val _locationList = MutableLiveData<List<Place>>()
    val locationList: LiveData<List<Place>> get() = _locationList

    private val collectedPlaces = mutableListOf<Place>()


    suspend fun searchLocationPlace(x: String, y: String, radius: Int) {
        viewModelScope.launch {
            try {
                val responses = mutableListOf<ResultKeyword>()

                // 각 카테고리별로 API 호출을 병렬로 수행합니다.
                listOf(
                    async { locationRepository.getSearchFacility("HP8", x, y, radius) },
                    async { locationRepository.getSearchFacility("PM9", x, y, radius) },
                    async { locationRepository.getSearchFacility("CE7", x, y, radius) },
                    async { locationRepository.getSearchFacility("FD6", x, y, radius) }
                ).awaitAll().forEach { response ->
                    responses.add(response)
                }

                // 각 호출의 결과를 모두 수집하여 리스트에 추가합니다.
                responses.forEach { response ->
                    collectedPlaces.addAll(response.documents)
                }

                // 모든 데이터가 수집되면 LiveData를 업데이트합니다.
                _locationList.value = collectedPlaces
            } catch (e: Exception) {
                Log.e("LocationViewModel", "Error: ${e.message}", e)
            }
        }
    }

    suspend fun searchEachLocationPlace(category:String, x:String, y:String, radius:Int){
        viewModelScope.launch {
            try {
                val response = locationRepository.getSearchFacility(category, x, y, radius)
                _locationList.value = response.documents
            }catch (e:Exception){

            }
        }
    }

    //오류 원인
//    fun clearCollectedPlaces() {
//        collectedPlaces.clear()
//        _locationList.value = emptyList()
//    }
}

