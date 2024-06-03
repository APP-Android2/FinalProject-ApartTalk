package kr.co.lion.application.finalproject_aparttalk.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.tasks.await
import kr.co.lion.application.finalproject_aparttalk.model.FireCheckModel

class FireCheckRepository {
    // firestore 인스턴스를 초기화한다.
    private val db = FirebaseFirestore.getInstance()
    // 'fire_check' 컬렉션을 참조한다.
    private val fireCheckCollection = db.collection("fire_check")

    // firestore에서 데이터를 비동기적으로 가져와 FireCheckModel 리스트로 변환한다.
    // 예외가 발생하면 빈 리스트를 반환한다.
    suspend fun getFireCheckData(): List<FireCheckModel> {
        return try {
            val snapshot = fireCheckCollection.get().await()
            snapshot.toObjects(FireCheckModel::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }
}