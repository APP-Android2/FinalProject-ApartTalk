package kr.co.lion.application.finalproject_aparttalk.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.tasks.await
import kr.co.lion.application.finalproject_aparttalk.model.ServiceModel

class ServiceRepository {

    private val db = FirebaseFirestore.getInstance()
    private val serviceCollection = db.collection("services")

    suspend fun addService(service: ServiceModel): Result<Nothing?> {
        return try {
            serviceCollection.add(service).await()
            Result.success(null)
        } catch (e: FirebaseFirestoreException) {
            Result.failure(e)
        }
    }

    fun getServices(): LiveData<List<ServiceModel>> {
        val servicesLiveData = MutableLiveData<List<ServiceModel>>()
        serviceCollection.addSnapshotListener { snapshot, e ->
            if (e != null) {
                return@addSnapshotListener
            }
            val services = snapshot?.documents?.map { document ->
                document.toObject(ServiceModel::class.java)!!
            } ?: emptyList()
            servicesLiveData.value = services
        }
        return servicesLiveData
    }
}