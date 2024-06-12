package kr.co.lion.application.finalproject_aparttalk.ui.parking.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kr.co.lion.application.finalproject_aparttalk.model.ParkingModel
import kr.co.lion.application.finalproject_aparttalk.repository.ParkingRepository

class ParkingViewModel : ViewModel() {

    private val parkingRepository = ParkingRepository()

    private val _parkingList = MutableLiveData<List<ParkingModel>>()
    var parkingList : LiveData<List<ParkingModel>> = _parkingList

    suspend fun insertParkingData(
        userUid:String, ownerNumber:String, ownerName:String, carNumber:String, visitDate:String
        , callback:(Boolean) -> Unit
    ){
        viewModelScope.launch {
            val parkingModel = ParkingModel(userUid, ownerNumber, ownerName, carNumber, visitDate)
            val success = withContext(Dispatchers.IO){
                try {
                    parkingRepository.parkingInfoData(parkingModel)
                    true
                }catch (e:Exception){
                    false
                }
            }
            callback(success)

        }
    }

    suspend fun getParkingResData(userUid:String){
        val parkingInfo = parkingRepository.parkingResInfo(userUid)
        val parkingInfoList = mutableListOf<ParkingModel>()

        parkingInfo.forEach {
            parkingInfoList.add(it)

            _parkingList.value = parkingInfoList
        }
    }

}