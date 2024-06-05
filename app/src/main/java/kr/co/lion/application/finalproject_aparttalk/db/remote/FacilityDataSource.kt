package kr.co.lion.application.finalproject_aparttalk.db.remote

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.storage.storage
import kotlinx.coroutines.tasks.await
import kr.co.lion.application.finalproject_aparttalk.App
import kr.co.lion.application.finalproject_aparttalk.model.FacilityModel

class FacilityDataSource {

    private val storage = Firebase.storage.reference


    //facility 내부의 모든 정보 가져오기
    suspend fun getFacilityInfo() :List<FacilityModel>{
        return try {
            val authUser = App.authRepository.getCurrentUser()
            val user = App.userRepository.getUser(authUser?.uid?:"")

            val query = Firebase.firestore.collection("Apartments").document(user?.apartmentUid?:"").collection("FacilityInfo")
            val querySnapshot = query.get().await()
            querySnapshot.map { it.toObject(FacilityModel::class.java) }

        }catch (e: Exception){
            emptyList()
        }
    }

    //이미지 데이터 받아오기
    suspend fun getFacilityInfoImg(image:String) : String?{
        val path = "facility/$image"
        return try {
            storage.child(path).downloadUrl.await().toString()
        }catch (e:Exception){
            null
        }
    }


}