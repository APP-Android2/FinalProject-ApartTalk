package kr.co.lion.application.finalproject_aparttalk.repository

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import kr.co.lion.application.finalproject_aparttalk.model.FireCheckModel
import kr.co.lion.application.finalproject_aparttalk.model.OperationInfoModel

class OperationInfoRepository(private val apartmentUid: String) {
    // firestore 인스턴스를 초기화한다.
    private val db = FirebaseFirestore.getInstance()
    // 'OperationInfo' 컬렉션을 참조한다.
    private val operationInfoCollection = db.collection("Apartments/$apartmentUid/OperationInfo")

    // firestore에서 데이터를 비동기적으로 가져와 OperationInfoModel 리스트로 변환한다.
    // 예외가 발생하면 빈 리스트를 반환한다.
    suspend fun getOperationInfoData(): List<OperationInfoModel> {
        return try {
            val snapshot = operationInfoCollection.get().await()
            snapshot.toObjects(OperationInfoModel::class.java)
        }catch (e:Exception) {
            emptyList()
        }
    }
}