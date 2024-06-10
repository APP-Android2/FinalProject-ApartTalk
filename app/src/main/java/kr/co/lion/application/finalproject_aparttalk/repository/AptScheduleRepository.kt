package kr.co.lion.application.finalproject_aparttalk.repository

import kr.co.lion.application.finalproject_aparttalk.db.AptScheduleDataSource

class AptScheduleRepository(aptScheduleDataSource: AptScheduleDataSource) {
    private val aptScheduleDataSource = AptScheduleDataSource()

    suspend fun selectingAptScheduleData(aptScheduleIdx: Int, apartmentUid: String) = aptScheduleDataSource.selectingAptScheduleData(aptScheduleIdx, apartmentUid)

    suspend fun gettingAptScheduleList(apartmentUid: String) = aptScheduleDataSource.gettingAptScheduleList(apartmentUid)
}