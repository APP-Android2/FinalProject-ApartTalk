package kr.co.lion.application.finalproject_aparttalk.repository

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import kr.co.lion.application.finalproject_aparttalk.db.OperationInfoDataSource
import kr.co.lion.application.finalproject_aparttalk.model.FireCheckModel
import kr.co.lion.application.finalproject_aparttalk.model.OperationInfoModel

class OperationInfoRepository(private val operationInfoDataSource: OperationInfoDataSource){
    suspend fun selectOperationInfoData(OperationInfoIdx: Int, apartmentUid: String) = operationInfoDataSource.selectOperationInfoData(OperationInfoIdx, apartmentUid)
    suspend fun gettingOperationInfoList(apartmentUid: String) = operationInfoDataSource.gettingOperationInfoList(apartmentUid)
}