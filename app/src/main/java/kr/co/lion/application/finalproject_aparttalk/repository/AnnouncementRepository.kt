package kr.co.lion.application.finalproject_aparttalk.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.tasks.await
import kr.co.lion.application.finalproject_aparttalk.model.AnnouncementModel

class AnnouncementRepository {

    private val db = FirebaseFirestore.getInstance()
    private val announcementCollection = db.collection("announcements")

    suspend fun addAnnouncement(announcement: AnnouncementModel): Result<Nothing?> {
        return try {
            announcementCollection.add(announcement).await()
            Result.success(null)
        } catch (e: FirebaseFirestoreException) {
            Result.failure(e)
        }
    }
}