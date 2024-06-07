package kr.co.lion.application.finalproject_aparttalk.ui.parking.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kr.co.lion.application.finalproject_aparttalk.model.FacilityResModel
import kr.co.lion.application.finalproject_aparttalk.model.ParkingModel
import kr.co.lion.application.finalproject_aparttalk.repository.ParkingRepository

class ParkingViewModel : ViewModel() {

    private val parkingRepository = ParkingRepository()

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

}