package kr.co.lion.application.finalproject_aparttalk.repository

import kr.co.lion.application.finalproject_aparttalk.db.remote.FacilityResDataSource
import kr.co.lion.application.finalproject_aparttalk.model.FacilityResModel

class FacilityResRepository {

    private var facilityResDataSource = FacilityResDataSource()

    suspend fun insertResData(facilityResModel: FacilityResModel) = facilityResDataSource.insertResInfo(facilityResModel)

    suspend fun getFacilityInfoData(userUid:String) = facilityResDataSource.getFacilityResInfo(userUid)


}