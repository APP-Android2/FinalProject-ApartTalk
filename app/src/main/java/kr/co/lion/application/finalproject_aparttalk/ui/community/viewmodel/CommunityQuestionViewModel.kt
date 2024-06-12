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

class CommunityQuestionViewModel: ViewModel() {
    private val communityPostRepository = CommunityPostRepository()
    private val communityCommentRepository = CommunityCommentRepository()
    var allList = mutableListOf<PostData>()
    var questionList = mutableListOf<PostData>()
    var commentList = mutableListOf<CommentData>()

    // 커뮤니티 글 리스트 질문 탭 라벨
    private val _textViewCommunityListLabelQuestion = MutableLiveData<String>()
    val textViewCommunityListLabelQuestion: LiveData<String> get() = _textViewCommunityListLabelQuestion

    // 커뮤니티 글 리스트 질문 탭 제목
    private val _textViewCommunityListTitleQuestion = MutableLiveData<String>()
    val textViewCommunityListTitleQuestion: LiveData<String> get() = _textViewCommunityListTitleQuestion

    // 커뮤니티 글 리스트 질문 탭 좋아요 수
    private val _textViewCommunityListLikeCntQuestion = MutableLiveData<Int>()
    val textViewCommunityListLikeCntQuestion: LiveData<Int> get() = _textViewCommunityListLikeCntQuestion

    // 커뮤니티 글 리스트 질문 탭 댓글 수
    private val _textViewCommunityListCommentCntQuestion = MutableLiveData<Int>()
    val textViewCommunityListCommentCntQuestion: LiveData<Int> get() = _textViewCommunityListCommentCntQuestion

    // 커뮤니티 글 리스트 질문 탭 날짜
    private val _textViewCommunityListDateQuestion = MutableLiveData<String>()
    val textViewCommunityListDateQuestion: LiveData<String> get() = _textViewCommunityListDateQuestion

    // 게시글 목록을 가져온다.
    suspend fun gettingCommunityPostList(postApartId: String) : MutableList<PostData> {
        return communityPostRepository.gettingCommunityPostList(postApartId)
    }

    // 이미지 데이터를 받아오는 메서드
    suspend fun gettingCommunityPostImage(context: Context, imageFileName:String, imageView: ImageView) {
        return communityPostRepository.gettingCommunityPostImage(context, imageFileName, imageView)
    }

    // 게시글 질문 리스트 받아오기
    suspend fun gettingCommunityQuestionList(postApartId: String) : MutableList<PostData> {
        val job1 = CoroutineScope(Dispatchers.Main).launch {
            allList = gettingCommunityPostList(postApartId)
            questionList.clear()
            allList.forEach {
                when(it.postType) {
                    "질문" -> questionList.add(it)
                }
            }
        }
        job1.join()

        return questionList
    }

    // 댓글 목록을 가져온다.
    suspend fun gettingCommunityCommentList(postApartId: String, postId: String):MutableList<CommentData>{
        return communityCommentRepository.gettingCommunityCommentList(postApartId, postId)
    }
}