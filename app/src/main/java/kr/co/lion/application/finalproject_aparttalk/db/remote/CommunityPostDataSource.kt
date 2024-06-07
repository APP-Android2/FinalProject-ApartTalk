package kr.co.lion.application.finalproject_aparttalk.db.remote

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.storage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kr.co.lion.application.finalproject_aparttalk.model.PostData
import kr.co.lion.application.finalproject_aparttalk.util.PostState

class CommunityPostDataSource {

        val apartmentCollection = Firebase.firestore.collection("Apartments")

        // 이미지 데이터를 firebase storage에 업로드는 메서드
        suspend fun uploadImage(context: Context, postIdx:Int, uriList:MutableList<Uri>) : MutableList<String> {

            val uploadFileList = mutableListOf<String>()
            uriList.forEach {
                val uploadFileName = "community_${postIdx}_${System.currentTimeMillis()}.jpg"

                uploadFileList.add(uploadFileName)

                val job1 = CoroutineScope(Dispatchers.IO).launch {
                    // Storage에 접근할 수 있는 객체를 가져온다.(폴더의 이름과 파일이름을 저장해준다.
                    val storageRef = Firebase.storage.reference.child("image/community/$uploadFileName")
                    // 업로드한다.
                    storageRef.putFile(it)
                }

                job1.join()
            }
            return  uploadFileList
        }

        // 이미지 데이터를 받아오는 메서드
        suspend fun gettingCommunityPostImage(context:Context, imageFileName:String, imageView: ImageView){
            val job1 = CoroutineScope(Dispatchers.IO).launch {
                // 이미지에 접근할 수 있는 객체를 가져온다.
                val storageRef = Firebase.storage.reference.child("image/community/$imageFileName")
                // 이미지의 주소를 가지고 있는 Uri 객체를 받아온다.
                val imageUri = storageRef.downloadUrl.await()
                // 이미지 데이터를 받아와 이미지 뷰에 보여준다.
                val job2 = CoroutineScope(Dispatchers.Main).launch {
                    Glide.with(context).load(imageUri).into(imageView)
                    // 이미지 뷰가 나타나게 한다.
                    imageView.visibility = View.VISIBLE
                }
                job2.join()

            }
            job1.join()
        }

        // 게시글 번호 시퀀스값을 가져온다.
        suspend fun getCommunityPostSequence():Int{

            var communityPostSequence = -1

            val job1 = CoroutineScope(Dispatchers.IO).launch {
                // 컬렉션에 접근할 수 있는 객체를 가져온다.
                val collectionReference = Firebase.firestore.collection("Sequence")
                // 게시글 번호 시퀀스 값을 가지고 있는 문서에 접근할 수 있는 객체를 가져온다.
                val documentReference = collectionReference.document("CommunityPostSequence")
                // 문서내에 있는 데이터를 가져올 수 있는 객체를 가져온다.
                val documentSnapShot = documentReference.get().await()
                communityPostSequence = documentSnapShot.getLong("value")?.toInt()!!
            }
            job1.join()

            return communityPostSequence
        }

        // 게시글 시퀀스 값을 업데이트 한다.
        suspend fun updateCommunityPostSequence(communityPostSequence:Int){
            val job1 = CoroutineScope(Dispatchers.IO).launch {
                // 컬렉션에 접근할 수 있는 객체를 가져온다.
                val collectionReference = Firebase.firestore.collection("Sequence")
                // 게시글 번호 시퀀스 값을 가지고 있는 문서에 접근할 수 있는 객체를 가져온다.
                val documentReference = collectionReference.document("CommunityPostSequence")
                // 저장할 데이터를 담을 HashMap을 만들어준다.
                val map = mutableMapOf<String, Long>()
                map["value"] = communityPostSequence.toLong()
                // 저장한다.
                documentReference.set(map)
            }
            job1.join()
        }

        // 게시글 정보를 저장한다.
        suspend fun insertCommunityPostData(postData: PostData){
            val job1 = CoroutineScope(Dispatchers.IO).launch {
                // 컬렉션에 접근할 수 있는 객체를 가져온다.
                val collectionReference = apartmentCollection.document(postData.postApartId).collection("CommunityPostData").document(postData.postId)
                // 컬럭션에 문서를 추가한다.
                // 문서를 추가할 때 객체나 맵을 지정한다.
                // 추가된 문서 내부의 필드는 객체가 가진 프로퍼티의 이름이나 맵에 있는 데이터의 이름과 동일하게 결정된다.
                collectionReference.set(postData)
            }
            job1.join()
        }

        // 글 번호를 이용해 글 데이터를 가져와 반환한다.
        suspend fun selectCommunityPostData(postApartId: String, postId: String): PostData?{

            var postData: PostData? = null

            val job1 = CoroutineScope(Dispatchers.IO).launch {
                // 컬렉션에 접근할 수 있는 객체를 가져온다.
                val collectionReference = apartmentCollection.document(postApartId).collection("CommunityPostData").document(postId).get().await()
                // 컬렉션이 가지고 있는 문서들 중에 postIdx 필드가 지정된 글 번호값하고 같은 Document들을 가져온다.
                // 가져온 글 정보를 객체에 담아서 반환 받는다.
                // postIdx 가 같은 글은 존재할 수가 없기 때문에 첫 번째 객체를 바로 추출해서 사용한다.
                // toObject : 지정한 클래스를 가지고 객체를 만든 다음 가져온 데이터의 필드의 이름과 동일한 이름의
                // 프로퍼티에 필드의 값을 담아준다.
                postData = collectionReference.toObject(PostData::class.java)
            }
            job1.join()

            return postData
        }

    // 게시글 목록을 가져온다.
    suspend fun gettingCommunityPostList(postApartId: String) : MutableList<PostData> {
        // 게시글 정보를 담을 리스트
        val communityPostList = mutableListOf<PostData>()

        val job1 = CoroutineScope(Dispatchers.IO).launch {
            // 컬렉션에 접근할 수 있는 객체를 가져온다.
            val collectionReference = apartmentCollection.document(postApartId).collection("CommunityPostData")
            // 게시글 상태가 정상 상태이고 게시글 번호를 기준으로 내림차순 정렬되게 데이터를 가져올 수 있는 Query 를 생성한다.
            // 게시글 상태가 정상 또는 수정 상태인 것만 가져오기 위한 조건 설정
            val validStatus = listOf(PostState.POST_STATE_NORMAL.number, PostState.POST_STATE_MODIFY.number)
            var query = collectionReference.whereIn("postState", validStatus)
            val querySnapshot = query.get().await()
            // 가져온 문서의 수 만큼 반복한다.
            querySnapshot.forEach {
                // 현재 번째의 문서를 객체로 받아온다.
                val postData = it.toObject(PostData::class.java)
                // 객체를 리스트에 담는다.
                communityPostList.add(postData)
            }
        }
        job1.join()

        return communityPostList
    }

    // 글의 상태를 변경하는 메서드
    suspend fun updateCommunityPostState(postApartId: String, postIdx: Int, newState:PostState) {
        val job1 = CoroutineScope(Dispatchers.IO).launch {
            // 컬렉션에 접근할 수 있는 객체를 가져온다.
            val collectionReference = apartmentCollection.document(postApartId).collection("CommunityPostData")
            // 컬렉션이 가지고 있는 문서들 중에 contentIdx 필드가 지정된 글 번호값하고 같은 Document 들을 가져온다.
            val query = collectionReference.whereEqualTo("postIdx", postIdx).get().await()

            // 저장할 데이터를 담을 HashMap을 만들어준다.
            val map = mutableMapOf<String, Any>()
            map["postState"] = newState.number.toLong()
            // 저장한다.
            // 가져온 문서 중 첫 번째 문서에 접근하여 데이터를 수정한다.
            query.documents[0].reference.update(map)
        }
        job1.join()
    }

    // 글 데이터를 수정하는 메서드
    suspend fun updateCommunityPostData(postData: PostData) {
        val job1 = CoroutineScope(Dispatchers.IO).launch {
            // 컬렉션에 접근할 수 있는 객체를 가져온다.
            val collectionReference = apartmentCollection.document(postData.postApartId).collection("CommunityPostData")
            // 컬렉션이 가지고 있는 문서들 중에 수정할 글 정보를 가져온다.
            val query = collectionReference.whereEqualTo("postIdx", postData.postIdx).get().await()

            // 저장할 데이터를 담을 HashMap을 만들어준다.
            val map = mutableMapOf<String, Any?>()
            map["postTitle"] = postData.postTitle
            map["postContent"] = postData.postContent
            map["postType"] = postData.postType

            // 저장한다.
            // 가져온 문서 중 첫 번째 문서에 접근하여 데이터를 수정한다.
            query.documents[0].reference.update(map)
        }
        job1.join()
    }
}