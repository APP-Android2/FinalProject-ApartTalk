package kr.co.lion.application.finalproject_aparttalk.repository

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import kr.co.lion.application.finalproject_aparttalk.model.VoteResultModel

class VoteResultRepository(private val firestore: FirebaseFirestore) {

    suspend fun getVoteResult(documentId: String): VoteResultModel? {
        return try {
            val document = firestore.collection("voteResults").document(documentId).get().await()
            document.toObject(VoteResultModel::class.java)
        } catch (e: Exception) {
            null
        }
    }
}
