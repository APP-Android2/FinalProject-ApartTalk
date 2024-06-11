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

class CommunityEtcViewModel: ViewModel() {
    private val communityPostRepository = CommunityPostRepository()
    private val communityCommentRepository = CommunityCommentRepository()
    var allList = mutableListOf<PostData>()
    var etcList = mutableListOf<PostData>()
    var commentList = mutableListOf<CommentData>()

    // 커뮤니티 글 리스트 기타 탭 라벨
    private val _textViewCommunityListLabelEtc = MutableLiveData<String>()
    val textViewCommunityListLabelEtc: LiveData<String> get() = _textViewCommunityListLabelEtc

    // 커뮤니티 글 리스트 기타 탭 제목
    private val _textViewCommunityListTitleEtc = MutableLiveData<String>()
    val textViewCommunityListTitleEtc: LiveData<String> get() = _textViewCommunityListTitleEtc

    // 커뮤니티 글 리스트 기타 탭 좋아요 수
    private val _textViewCommunityListLikeCntEtc = MutableLiveData<Int>()
    val textViewCommunityListLikeCntEtc: LiveData<Int> get() = _textViewCommunityListLikeCntEtc

    // 커뮤니티 글 리스트 기타 탭 댓글 수
    private val _textViewCommunityListCommentCntEtc = MutableLiveData<Int>()
    val textViewCommunityListCommentCntEtc: LiveData<Int> get() = _textViewCommunityListCommentCntEtc

    // 커뮤니티 글 리스트 기타 탭 날짜
    private val _textViewCommunityListDateEtc = MutableLiveData<String>()
    val textViewCommunityListDateEtc: LiveData<String> get() = _textViewCommunityListDateEtc

    // 게시글 목록을 가져온다.
    suspend fun gettingCommunityPostList(postApartId: String) : MutableList<PostData> {
        return communityPostRepository.gettingCommunityPostList(postApartId)
    }

    // 이미지 데이터를 받아오는 메서드
    suspend fun gettingCommunityPostImage(context: Context, imageFileName:String, imageView: ImageView) {
        return communityPostRepository.gettingCommunityPostImage(context, imageFileName, imageView)
    }

    // 게시글 기타 리스트 받아오기
    suspend fun gettingCommunityEtcList(postApartId: String) : MutableList<PostData> {
        val job1 = CoroutineScope(Dispatchers.Main).launch {
            allList = gettingCommunityPostList(postApartId)
            etcList.clear()
            allList.forEach {
                when(it.postType) {
                    "기타" -> etcList.add(it)
                }
            }
        }
        job1.join()

        return etcList
    }

    // 댓글 목록을 가져온다.
    suspend fun gettingCommunityCommentList(postApartId: String, postId: String):MutableList<CommentData>{
        return communityCommentRepository.gettingCommunityCommentList(postApartId, postId)
    }
}