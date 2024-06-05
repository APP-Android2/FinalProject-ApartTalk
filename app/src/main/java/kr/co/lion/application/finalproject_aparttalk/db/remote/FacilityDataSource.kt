package kr.co.lion.application.finalproject_aparttalk.db.remote

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await
import kr.co.lion.application.finalproject_aparttalk.App
import kr.co.lion.application.finalproject_aparttalk.model.FacilityModel

class FacilityDataSource {

    val authUser = App.authRepository.getCurrentUser()

    private val facilityInfo = Firebase.firestore.collection("Apartments").document(authUser?.uid?:"").collection("FacilityInfo")


    //facility 내부의 모든 정보 가져오기
    suspend fun getFacilityInfo() :List<FacilityModel>{
        return try {
            val query = facilityInfo
            val querySnapshot = query.get().await()
            querySnapshot.map { it.toObject(FacilityModel::class.java) }
        }catch (e: Exception){
            emptyList()
        }
    }


}