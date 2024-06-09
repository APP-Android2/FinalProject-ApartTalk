package kr.co.lion.application.finalproject_aparttalk.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await
import kr.co.lion.application.finalproject_aparttalk.model.ApartmentModel
import kr.co.lion.application.finalproject_aparttalk.model.InquiryModel
import kr.co.lion.application.finalproject_aparttalk.model.UserModel
import kr.co.lion.application.finalproject_aparttalk.db.remote.UserDataSource
import kr.co.lion.application.finalproject_aparttalk.db.remote.ApartmentDataSource

class InquiryRepository {

    private val db: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }
    private val inquiryCollection = db.collection("Inquiries")
    private val userDataSource = UserDataSource()
    private val apartmentDataSource = ApartmentDataSource()

    suspend fun createInquiry(inquiry: InquiryModel) {
        inquiryCollection.document().set(inquiry).await()
    }

    suspend fun getInquiries(apartmentUid: String): List<InquiryModel> {
        val snapshot = inquiryCollection.whereEqualTo("apartmentUid", apartmentUid).get().await()
        return snapshot.documents.mapNotNull { it.toObject<InquiryModel>() }
    }

    suspend fun getUser(uid: String): UserModel? {
        return userDataSource.getUser(uid)
    }

    suspend fun getApartment(uid: String): ApartmentModel? {
        return apartmentDataSource.getApartment(uid)
    }
}
