package kr.co.lion.application.finalproject_aparttalk.repository

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import kr.co.lion.application.finalproject_aparttalk.db.OperationInfoDataSource
import kr.co.lion.application.finalproject_aparttalk.model.FireCheckModel
import kr.co.lion.application.finalproject_aparttalk.model.OperationInfoModel

class OperationInfoRepository(private val operationInfoDataSource: OperationInfoDataSource){
    suspend fun selectingOperationInfoData(OperationInfoIdx: Int, apartmentUid: String) = operationInfoDataSource.selectingOperationInfoData(OperationInfoIdx, apartmentUid)
    suspend fun gettingOperationInfoList(apartmentUid: String) = operationInfoDataSource.gettingOperationInfoList(apartmentUid)
}