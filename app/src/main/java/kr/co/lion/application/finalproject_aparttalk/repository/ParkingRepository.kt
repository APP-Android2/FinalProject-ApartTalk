package kr.co.lion.application.finalproject_aparttalk.repository

import kr.co.lion.application.finalproject_aparttalk.db.remote.ParkingDataSource
import kr.co.lion.application.finalproject_aparttalk.model.ParkingModel

class ParkingRepository {

    private val parkingDataSource = ParkingDataSource()

    suspend fun parkingInfoData(parkingModel: ParkingModel) = parkingDataSource.insertParkingData(parkingModel)

    suspend fun parkingResInfo(userUid:String) = parkingDataSource.getParkingResInfo(userUid)

}