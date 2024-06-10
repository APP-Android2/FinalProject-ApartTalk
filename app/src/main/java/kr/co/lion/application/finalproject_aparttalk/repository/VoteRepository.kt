package kr.co.lion.application.finalproject_aparttalk.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import kr.co.lion.application.finalproject_aparttalk.model.VoteResultModel

class VoteRepository(private val firestore: FirebaseFirestore) {

    suspend fun getVoteData(voteDocumentId: String): VoteResultModel? {
        return try {
            val document = firestore.collection("votes").document(voteDocumentId).get().await()
            document.toObject(VoteResultModel::class.java)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getUserVoteStatus(voteDocumentId: String, userIdx: String): Boolean {
        return try {
            val document = firestore.collection("votes").document(voteDocumentId).get().await()
            val voteData = document.toObject(VoteResultModel::class.java)
            voteData?.userVotes?.get(userIdx) ?: false
        } catch (e: Exception) {
            false
        }
    }

    suspend fun updateUserVoteStatus(voteDocumentId: String, userIdx: String, hasVoted: Boolean): Boolean {
        return try {
            val documentRef = firestore.collection("votes").document(voteDocumentId)
            firestore.runTransaction { transaction ->
                val snapshot = transaction.get(documentRef)
                val voteData = snapshot.toObject(VoteResultModel::class.java)
                val userVotes = voteData?.userVotes?.toMutableMap() ?: mutableMapOf()
                userVotes[userIdx] = hasVoted
                transaction.update(documentRef, "userVotes", userVotes)
            }.await()
            Log.d("VoteRepository", "User vote status updated successfully for user: $userIdx")
            true
        } catch (e: Exception) {
            Log.e("VoteRepository", "Error updating user vote status", e)
            false
        }
    }

    suspend fun getVoteItems(): List<VoteResultModel> {
        return try {
            val snapshot = firestore.collection("votes").get().await()
            snapshot.documents.mapNotNull { it.toObject(VoteResultModel::class.java) }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getPastVotes(): List<VoteResultModel> {
        return try {
            val snapshot = firestore.collection("voteResults").get().await()
            snapshot.documents.mapNotNull { it.toObject(VoteResultModel::class.java) }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
