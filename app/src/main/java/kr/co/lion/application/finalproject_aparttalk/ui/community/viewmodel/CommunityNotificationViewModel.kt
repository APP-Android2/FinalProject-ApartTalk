package kr.co.lion.application.finalproject_aparttalk.ui.community.viewmodel

import android.content.Context
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.model.CommentData
import kr.co.lion.application.finalproject_aparttalk.model.PostData
import kr.co.lion.application.finalproject_aparttalk.repository.CommunityCommentRepository
import kr.co.lion.application.finalproject_aparttalk.repository.CommunityPostRepository

class CommunityNotificationViewModel: ViewModel() {

    private val communityPostRepository = CommunityPostRepository()
    private val communityCommentRepository = CommunityCommentRepository()
    var allList = mutableListOf<PostData>()
    var notificationList = mutableListOf<PostData>()
    var commentList = mutableListOf<CommentData>()

    // 커뮤니티 글 리스트 공지 탭 라벨
    private val _textViewCommunityListLabelNotification = MutableLiveData<String>()
    val textViewCommunityListLabelNotification: LiveData<String> get() = _textViewCommunityListLabelNotification

    // 커뮤니티 글 리스트 공지 탭 제목
    private val _textViewCommunityListTitleNotification = MutableLiveData<String>()
    val textViewCommunityListTitleNotification: LiveData<String> get() = _textViewCommunityListTitleNotification

    // 커뮤니티 글 리스트 공지 탭 좋아요 수
    private val _textViewCommunityListLikeCntNotification = MutableLiveData<Int>()
    val textViewCommunityListLikeCntNotification: LiveData<Int> get() = _textViewCommunityListLikeCntNotification

    // 커뮤니티 글 리스트 공지 탭 댓글 수
    private val _textViewCommunityListCommentCntNotification = MutableLiveData<Int>()
    val textViewCommunityListCommentCntNotification: LiveData<Int> get() = _textViewCommunityListCommentCntNotification

    // 커뮤니티 글 리스트 공지 탭 날짜
    private val _textViewCommunityListDateNotification = MutableLiveData<String>()
    val textViewCommunityListDateNotification: LiveData<String> get() = _textViewCommunityListDateNotification

    // 게시글 목록을 가져온다.
    suspend fun gettingCommunityPostList(postApartId: String) : MutableList<PostData> {
        return communityPostRepository.gettingCommunityPostList(postApartId)
    }

    // 이미지 데이터를 받아오는 메서드
    suspend fun gettingCommunityPostImage(context: Context, imageFileName:String, imageView: ImageView) {
        return communityPostRepository.gettingCommunityPostImage(context, imageFileName, imageView)
    }

    // 게시글 공지 리스트 받아오기
    suspend fun gettingCommunityNotificationList(postApartId: String) : MutableList<PostData> {
        val job1 = CoroutineScope(Dispatchers.Main).launch {
            allList = gettingCommunityPostList(postApartId)
            notificationList.clear()
            allList.forEach {
                when(it.postType) {
                    "공지" -> notificationList.add(it)
                }
            }
        }
        job1.join()

        return notificationList
    }

    // 댓글 목록을 가져온다.
    suspend fun gettingCommunityCommentList(postApartId: String, postId: String):MutableList<CommentData>{
        return communityCommentRepository.gettingCommunityCommentList(postApartId, postId)
    }
}