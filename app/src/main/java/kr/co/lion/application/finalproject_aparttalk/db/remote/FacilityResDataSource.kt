package kr.co.lion.application.finalproject_aparttalk.db.remote

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kr.co.lion.application.finalproject_aparttalk.model.FacilityResModel

class FacilityResDataSource {

    private val db = Firebase.firestore

    //예약 정보를 저장한다
    suspend fun insertResInfo(facilityResModel: FacilityResModel){
        val job1 = CoroutineScope(Dispatchers.IO).launch {
            val collectionReference = db.collection("FacilityResInfo")
            collectionReference.add(facilityResModel)
        }
        job1.join()
    }

    //userUid 값으로 정보를 가져온다
    suspend fun getFacilityResInfo(userUid:String) : FacilityResModel? {
        var facilityResModel:FacilityResModel? = null

        var job1 = CoroutineScope(Dispatchers.IO).launch {
            val collectionReference = db.collection("FacilityResInfo")
            val querySnapshot = collectionReference.whereEqualTo("userUid", userUid).get().await()
            if (querySnapshot.isEmpty == false){
                facilityResModel = querySnapshot.documents[0].toObject(FacilityResModel::class.java)
            }
        }
        job1.join()

        return facilityResModel
    }
}