package kr.co.lion.application.finalproject_aparttalk.db.remote

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kr.co.lion.application.finalproject_aparttalk.model.CommentData
import kr.co.lion.application.finalproject_aparttalk.util.CommentState

class CommunityCommentDataSource {

        val apartmentCollection = Firebase.firestore.collection("Apartments")

        // 댓글 번호 시퀀스값을 가져온다.
        suspend fun getCommunityCommentSequence():Int{

            var communityCommentSequence = -1

            val job1 = CoroutineScope(Dispatchers.IO).launch {
                // 컬렉션에 접근할 수 있는 객체를 가져온다.
                val collectionReference = Firebase.firestore.collection("Sequence")
                // 댓글 번호 시퀀스 값을 가지고 있는 문서에 접근할 수 있는 객체를 가져온다.
                val documentReference = collectionReference.document("CommunityCommentSequence")
                // 문서내에 있는 데이터를 가져올 수 있는 객체를 가져온다.
                val documentSnapShot = documentReference.get().await()
                communityCommentSequence = documentSnapShot.getLong("value")?.toInt()!!
            }
            job1.join()

            return communityCommentSequence
        }

        // 댓글 시퀀스 값을 업데이트 한다.
        suspend fun updateCommunityCommentSequence(commentSequence:Int){
            val job1 = CoroutineScope(Dispatchers.IO).launch {
                // 컬렉션에 접근할 수 있는 객체를 가져온다.
                val collectionReference = Firebase.firestore.collection("Sequence")
                // 댓글 번호 시퀀스 값을 가지고 있는 문서에 접근할 수 있는 객체를 가져온다.
                val documentReference = collectionReference.document("CommunityCommentSequence")
                // 저장할 데이터를 담을 HashMap을 만들어준다.
                val map = mutableMapOf<String, Long>()
                map["value"] = commentSequence.toLong()
                // 저장한다.
                documentReference.set(map)
            }
            job1.join()
        }

        // 댓글 정보를 저장한다.
        suspend fun insertCommunityCommentData(postApartId: String, commentData: CommentData){
            val job1 = CoroutineScope(Dispatchers.IO).launch {
                // 컬렉션에 접근할 수 있는 객체를 가져온다.
                val collectionReference = apartmentCollection.document(postApartId).collection("CommunityPostData").document(commentData.commentPostId).collection("CommunityCommentData").document(commentData.commentId)
                // 컬럭션에 문서를 추가한다.
                // 문서를 추가할 때 객체나 맵을 지정한다.
                // 추가된 문서 내부의 필드는 객체가 가진 프로퍼티의 이름이나 맵에 있는 데이터의 이름과 동일하게 결정된다.
                collectionReference.set(commentData)
            }
            job1.join()
        }

        // 댓글 목록을 가져온다.
        suspend fun gettingCommunityCommentList(postApartId: String, postId: String):MutableList<CommentData>{
            // 댓글 정보를 담을 리스트
            val communityCommentList = mutableListOf<CommentData>()

            val job1 = CoroutineScope(Dispatchers.IO).launch {
                // 컬렉션에 접근할 수 있는 객체를 가져온다.
                val collectionReference = apartmentCollection.document(postApartId).collection("CommunityPostData").document(postId).collection("CommunityCommentData")
                // 댓글 상태가 정상 상태이고 댓글 번호를 기준으로 내림차순 정렬되게 데이터를 가져올 수 있는
                // Query를 생성한다.
                // 댓글 상태가 정상과 수정 상태인 것만..
                var query = collectionReference.whereIn("commentState", listOf(CommentState.COMMENT_STATE_NORMAL.number, CommentState.COMMENT_STATE_MODIFY.number))
                // 글 번호에 해당하는 것들만
                query = query.whereEqualTo("commentPostId", postId)
                // 댓글 번호를 기준으로 내림 차순 정렬..
                query = query.orderBy("commentIdx", Query.Direction.ASCENDING)
                val queryShapshot = query.get().await()
                // 가져온 문서의 수 만큼 반복한다.
                queryShapshot.forEach {
                    // 현재 번째의 문서를 객체로 받아온다.
                    val commentData = it.toObject(CommentData::class.java)
                    // 객체를 리스트에 담는다.
                    communityCommentList.add(commentData)
                }

            }
            job1.join()

            return communityCommentList
        }

        // 댓글의 상태를 변경하는 메서드
        suspend fun updateCommunityCommentState(postApartId: String, commentData: CommentData, newState:CommentState){
            val job1 = CoroutineScope(Dispatchers.IO).launch {
                // 저장할 데이터를 담을 HashMap을 만들어준다.
                val map = mutableMapOf<String, Any>()
                map["commentState"] = newState.number.toLong()
                // 컬렉션에 접근할 수 있는 객체를 가져온다.
                val collectionReference = apartmentCollection.document(postApartId).collection("CommunityPostData").document(commentData.commentPostId).collection("CommunityCommentData").document(commentData.commentId).update(map)
            }
            job1.join()
        }

        // 댓글의 내용을 변경하는 메서드
        suspend fun updateCommunityCommentData(postApartId: String, commentData: CommentData, mapComment: MutableMap<String, Any>){
            val job1 = CoroutineScope(Dispatchers.IO).launch {
                apartmentCollection.document(postApartId).collection("CommunityPostData").document(commentData.commentPostId).collection("CommunityCommentData").document(commentData.commentId).update(mapComment)
            }
            job1.join()
        }
}