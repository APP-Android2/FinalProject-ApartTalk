package kr.co.lion.application.finalproject_aparttalk.db.remote

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kr.co.lion.application.finalproject_aparttalk.model.CommentData
import kr.co.lion.application.finalproject_aparttalk.model.LikeData
import kr.co.lion.application.finalproject_aparttalk.util.CommentState

class CommunityLikeDataSource {
    val apartmentCollection = Firebase.firestore.collection("Apartments")

    // 좋아요 번호 시퀀스값을 가져온다.
    suspend fun getLikeSequence():Int{

        var likeSequence = -1

        val job1 = CoroutineScope(Dispatchers.IO).launch {
            // 컬렉션에 접근할 수 있는 객체를 가져온다.
            val collectionReference = Firebase.firestore.collection("Sequence")
            // 댓글 번호 시퀀스 값을 가지고 있는 문서에 접근할 수 있는 객체를 가져온다.
            val documentReference = collectionReference.document("LikeSequence")
            // 문서내에 있는 데이터를 가져올 수 있는 객체를 가져온다.
            val documentSnapShot = documentReference.get().await()
            likeSequence = documentSnapShot.getLong("value")?.toInt()!!
        }
        job1.join()

        return likeSequence
    }

    // 좋아요 시퀀스 값을 업데이트 한다.
    suspend fun updateLikeSequence(likeSequence:Int){
        val job1 = CoroutineScope(Dispatchers.IO).launch {
            // 컬렉션에 접근할 수 있는 객체를 가져온다.
            val collectionReference = Firebase.firestore.collection("Sequence")
            // 댓글 번호 시퀀스 값을 가지고 있는 문서에 접근할 수 있는 객체를 가져온다.
            val documentReference = collectionReference.document("LikeSequence")
            // 저장할 데이터를 담을 HashMap을 만들어준다.
            val map = mutableMapOf<String, Long>()
            map["value"] = likeSequence.toLong()
            // 저장한다.
            documentReference.set(map)
        }
        job1.join()
    }

    // 게시글의 좋아요 정보를 저장한다.
    suspend fun insertLikeData(postApartId: String, likeData: LikeData){
        val job1 = CoroutineScope(Dispatchers.IO).launch {
            // 컬렉션에 접근할 수 있는 객체를 가져온다.
            val collectionReference = apartmentCollection.document(postApartId).collection("CommunityPostData").document(likeData.likePostId).collection("LikeData").document(likeData.likeId)
            // 컬럭션에 문서를 추가한다.
            // 문서를 추가할 때 객체나 맵을 지정한다.
            // 추가된 문서 내부의 필드는 객체가 가진 프로퍼티의 이름이나 맵에 있는 데이터의 이름과 동일하게 결정된다.
            collectionReference.set(likeData)
        }
        job1.join()
    }

    // 게시글의 좋아요 정보 삭제
    suspend fun deleteLikeData(postApartId: String, likeData: LikeData) {
        val job1 = CoroutineScope(Dispatchers.IO).launch {
            val collectionReference = apartmentCollection.document(postApartId).collection("CommunityPostData").document(likeData.likePostId).collection("LikeData").document(likeData.likeId)

            val query = collectionReference.get().await()
            query.reference.delete()
        }
        job1.join()
    }

    // 좋아요 목록을 가져온다.
    suspend fun gettingLikeList(postApartId: String, postId: String): MutableList<LikeData>{
        // 댓글 정보를 담을 리스트
        val likeList = mutableListOf<LikeData>()

        val job1 = CoroutineScope(Dispatchers.IO).launch {
            val collectionReference = apartmentCollection.document(postApartId).collection("CommunityPostData").document(postId).collection("LikeData")
            var query = collectionReference.get().await()
            // 가져온 문서의 수 만큼 반복한다.
            query.forEach {
                // 현재 번째의 문서를 객체로 받아온다.
                val likeData = it.toObject(LikeData::class.java)
                // 객체를 리스트에 담는다.
                likeList.add(likeData)
            }

        }
        job1.join()

        return likeList
    }
}