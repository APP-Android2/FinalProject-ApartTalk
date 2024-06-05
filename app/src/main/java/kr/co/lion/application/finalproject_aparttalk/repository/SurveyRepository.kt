package kr.co.lion.application.finalproject_aparttalk.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import kr.co.lion.application.finalproject_aparttalk.model.Survey

class SurveyRepository {
    private val db = FirebaseFirestore.getInstance()
    private val surveyCollection = db.collection("Surveys")
    private val surveyVotesCollection = db.collection("SurveyVotes")

    suspend fun addSurvey(survey: Survey): Boolean {
        return try {
            // 현재 컬렉션의 가장 높은 surveyIdx 값을 가져옴
            val highestIdxSnapshot = surveyCollection
                .orderBy("surveyIdx", Query.Direction.DESCENDING)
                .limit(1)
                .get()
                .await()
                .documents
            val currentIdx = if (highestIdxSnapshot.isNotEmpty()) {
                highestIdxSnapshot[0].getLong("surveyIdx") ?: 0
            } else {
                0
            }
            val newIdx = currentIdx + 1

            // Firestore 트랜잭션을 사용하여 새로운 문서를 추가하거나 업데이트
            db.runTransaction { transaction ->
                val newSurvey = survey.copy(surveyIdx = newIdx.toInt())
                val newDocRef = surveyCollection.document("survey_${survey.surveyTitle.hashCode()}")

                if (transaction.get(newDocRef).exists()) {
                    // 문서가 존재하는 경우 업데이트
                    transaction.update(newDocRef, newSurvey.toMap())
                } else {
                    // 문서가 존재하지 않는 경우 새 문서 생성
                    transaction.set(newDocRef, newSurvey)
                }
            }.await()
            true
        } catch (e: Exception) {
            println("SurveyRepository: Error adding survey: ${e.message}")
            false
        }
    }

    suspend fun getSurveys(): List<Survey> {
        return try {
            val snapshot = surveyCollection.get().await()
            snapshot.documents.map { document ->
                document.toObject(Survey::class.java) ?: Survey()
            }
        } catch (e: Exception) {
            println("SurveyRepository: Error getting surveys: ${e.message}")
            emptyList()
        }
    }

    suspend fun saveSurveyVote(userId: String, surveyId: String, selectedOption: Int): Boolean {
        return try {
            val surveyVoteDocRef = surveyVotesCollection.document("$userId$surveyId")
            surveyVoteDocRef.set(mapOf(
                "userId" to userId,
                "surveyId" to surveyId,
                "selectedOption" to selectedOption,
                "timestamp" to System.currentTimeMillis()
            )).await()
            true
        } catch (e: Exception) {
            println("SurveyRepository: Error saving survey vote: ${e.message}")
            false
        }
    }

    suspend fun hasUserVoted(userId: String, surveyId: String): Boolean {
        return try {
            val surveyVoteDocRef = surveyVotesCollection.document("$userId$surveyId")
            val voteSnapshot = surveyVoteDocRef.get().await()
            voteSnapshot.exists()
        } catch (e: Exception) {
            println("SurveyRepository: Error checking user vote: ${e.message}")
            false
        }
    }
}
