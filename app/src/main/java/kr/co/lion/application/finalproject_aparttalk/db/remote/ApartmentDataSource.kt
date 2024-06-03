package kr.co.lion.application.finalproject_aparttalk.db.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await
import kr.co.lion.application.finalproject_aparttalk.model.ApartmentModel

class ApartmentDataSource {

    private val db: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }
    private val apartmentCollection = db.collection("Apartments")

    suspend fun getApartment(uid: String): ApartmentModel? {
        val snapshot = apartmentCollection.document(uid).get().await()
        val apartment = snapshot.toObject<ApartmentModel>()
        return apartment
    }

    suspend fun getApartmentList(): List<ApartmentModel>{
        val snapshot = apartmentCollection.get().await()
        val apartmentList = snapshot.documents.mapNotNull { it.toObject<ApartmentModel>() }
        return apartmentList
    }
}