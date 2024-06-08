package kr.co.lion.application.finalproject_aparttalk.repository

import kr.co.lion.application.finalproject_aparttalk.db.OperationInfoDataSource

class OperationInfoRepository(operationInfoDataSource: OperationInfoDataSource) {
    private val operationInfoDataSource = OperationInfoDataSource()
    suspend fun selectingOperationInfoData(OperationInfoIdx: Int, apartmentUid: String) = operationInfoDataSource.selectingOperationInfoData(OperationInfoIdx, apartmentUid)
    suspend fun gettingOperationInfoList(apartmentUid: String) = operationInfoDataSource.gettingOperationInfoList(apartmentUid)
}