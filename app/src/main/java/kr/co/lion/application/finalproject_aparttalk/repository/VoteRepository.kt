package kr.co.lion.application.finalproject_aparttalk.repository

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import kr.co.lion.application.finalproject_aparttalk.model.VoteResultModel

class VoteRepository(private val firestore: FirebaseFirestore) {

    suspend fun getVoteData(): VoteResultModel? {
        return try {
            val document = firestore.collection("votes").document("your_vote_document_id").get().await()
            document.toObject(VoteResultModel::class.java)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getVoteItems(): List<VoteResultModel> {
        return try {
            val snapshot = firestore.collection("voteResults").get().await()
            snapshot.documents.mapNotNull { it.toObject(VoteResultModel::class.java) }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getUserVoteStatus(userIdx: String): Boolean {
        return try {
            val document = firestore.collection("votes").document("your_vote_document_id").get().await()
            val voteData = document.toObject(VoteResultModel::class.java)
            voteData?.userVotes?.get(userIdx) ?: false
        } catch (e: Exception) {
            false
        }
    }

    suspend fun updateUserVoteStatus(userIdx: String, hasVoted: Boolean): Boolean {
        return try {
            val documentRef = firestore.collection("votes").document("your_vote_document_id")
            firestore.runTransaction { transaction ->
                val snapshot = transaction.get(documentRef)
                val voteData = snapshot.toObject(VoteResultModel::class.java)
                val userVotes = voteData?.userVotes?.toMutableMap() ?: mutableMapOf()
                userVotes[userIdx] = hasVoted
                transaction.update(documentRef, "userVotes", userVotes)
            }.await()
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun getVoteResult(documentId: String): VoteResultModel? {
        return try {
            val document = firestore.collection("voteResults").document(documentId).get().await()
            document.toObject(VoteResultModel::class.java)
        } catch (e: Exception) {
            null
        }
    }
}
