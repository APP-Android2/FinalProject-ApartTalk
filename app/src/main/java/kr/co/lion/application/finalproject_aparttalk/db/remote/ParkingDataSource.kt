package kr.co.lion.application.finalproject_aparttalk.db.remote

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

}