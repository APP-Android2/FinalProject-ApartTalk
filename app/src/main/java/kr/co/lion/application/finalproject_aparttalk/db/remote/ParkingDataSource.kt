package kr.co.lion.application.finalproject_aparttalk.db.remote

import com.google.firebase.Firebase
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kr.co.lion.application.finalproject_aparttalk.model.FacilityResModel
import kr.co.lion.application.finalproject_aparttalk.model.ParkingModel

class ParkingDataSource {

    private val db = Firebase.firestore

    suspend fun insertParkingData(parkingModel: ParkingModel){
        val job1 = CoroutineScope(Dispatchers.IO).launch {
            val collectionReference = db.collection("ParkingInfo")
            collectionReference.add(parkingModel)
        }
        job1.join()
    }

    //userUid 값으로 정보를 가져온다
    suspend fun getParkingResInfo(userUid:String) : List<ParkingModel> {
        return try {
            val querySnapshot = db.collection("ParkingInfo").whereEqualTo("userUid", userUid)
                .get().await()

            querySnapshot.toObjects(ParkingModel::class.java)
        }catch (e:Exception){
            emptyList()
        }
    }

}