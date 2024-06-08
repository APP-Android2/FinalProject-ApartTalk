package kr.co.lion.application.finalproject_aparttalk.db

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kr.co.lion.application.finalproject_aparttalk.model.OperationInfoModel

class OperationInfoDataSource {

    // 글 번호를 이용해 글 데이터를 가져와 반환한다.
    suspend fun selectingOperationInfoData(OperationInfoIdx: Int, apartmentUid: String): OperationInfoModel? {

        var operationInfoModel: OperationInfoModel? = null

        val job1 = CoroutineScope(Dispatchers.IO).launch {
            // 컬렉션에 접근할 수 있는 객체를 가져온다
            val collectionReference = Firebase.firestore.collection("Apartments/$apartmentUid/OperationInfo")
            // 컬렉션이 가지고 있는 문서들 중에 OperationInfoIdx 필드가 지정된 글 번호값하고 같은 Document들을 가져온다.
            val querySnapshot = collectionReference.whereEqualTo("OperationInfoIdx", OperationInfoIdx).get().await()
            // 가져온 글 정보를 객체에 담아서 반환 받는다.
            // OperationInfoIdx 가 같은 글은 존재할 수가 없기 때문에 첫 번째 객체를 바로 추출해서 사용한다.
            // toObject : 지정한 클래스를 가지고 객체를 만든 다음 가져온 데이터의 필드의 이름과 동일한 이름의
            // 프로퍼티에 필드의 값을 담아준다.
            operationInfoModel = querySnapshot.documents[0].toObject(OperationInfoModel::class.java)
        }
        job1.join()

        return operationInfoModel
    }

    // 게시글 목록을 가져온다.
    suspend fun gettingOperationInfoList(apartmentUid: String) : MutableList<OperationInfoModel> {
        // 게시글 정보를 담을 리스트
        val operationInfoList = mutableListOf<OperationInfoModel>()

        val job1 = CoroutineScope(Dispatchers.IO).launch {
            // 컬렉션에 접근할 수 있는 객체를 가져온다.
            val collectionReference = Firebase.firestore.collection("Apartments/$apartmentUid/OperationInfo")

            val querySnapshot = collectionReference.get().await()
            // 가져온 문서의 수 만큼 반복한다.
            querySnapshot.forEach {
                // 현재 번째의 문서를 객체로 받아온다.
                val operationInfoModel = it.toObject(OperationInfoModel::class.java)
                // 객체를 리스트에 담는다.
                operationInfoList.add(operationInfoModel)
            }
        }
        job1.join()

        // 로그를 추가하여 데이터 출력
        Log.d("OperationInfoDataSource", "Operation Info List: $operationInfoList")

        return operationInfoList
    }
}