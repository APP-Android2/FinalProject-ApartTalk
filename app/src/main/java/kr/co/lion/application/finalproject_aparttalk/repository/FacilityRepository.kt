package kr.co.lion.application.finalproject_aparttalk.repository

import kr.co.lion.application.finalproject_aparttalk.db.remote.FacilityDataSource

class FacilityRepository {

    private val facilityDataSource = FacilityDataSource()

    suspend fun getFacilityInfoData() = facilityDataSource.getFacilityInfo()

    suspend fun getFacilityImage(image:String) = facilityDataSource.getFacilityInfoImg(image)
}